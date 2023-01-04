package com.junu.dailycomposeui.animation.visibility

import android.animation.TimeInterpolator
import android.view.animation.BounceInterpolator
import androidx.compose.animation.*
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// https://medium.com/mobile-app-development-publication/learn-jetpack-compose-animated-visibility-6595d45938a1
// https://github.com/elye/demo_android_jetpack_compose_animation
// https://developer.android.com/jetpack/compose/animation

fun TimeInterpolator.toEasing() = Easing { x -> getInterpolation(x) }

@Composable
fun AnimatedVisibilityViewStep1() {
    // 에니메이션 없는 숨기고 보이고
    var visible by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (visible) {
            Text(text = "Hello, world!")
        }
        
        Button(onClick = { visible = !visible }) {
            Text(text = "Click Me")
        }
    }
}

@Composable
fun AnimatedVisibilityViewStep2() {
    // 에니메이션 있는 숨기고 보이고
    var visible by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(visible = visible) {
            Text(text = "Hello, world!")
        }

        Button(onClick = { visible = !visible }) {
            Text(text = "Click Me")
        }
    }
}

@Composable
fun AnimatedVisibilityViewStep3() {
    // fade 에니메이션 있는 숨기고 보이고 : default 300ms
    var visible by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(text = "Hello, world!")
        }

        Button(onClick = { visible = !visible }) {
            Text(text = "Click Me")
        }
    }
}

@Composable
fun AnimatedVisibilityViewStep4() {

    // fade 에니메이션 있는 숨기고 보이고 : 1000ms

    var visible by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            Text(text = "Hello, world!")
        }

        Button(onClick = { visible = !visible }) {
            Text(text = "Click Me")
        }
    }
}

@Composable
fun AnimatedVisibilityViewStep5() {
    // fade 에니메이션 있는 숨기고 보이고 : bouncing effects
    var visible by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(1000)) +
            expandVertically(
                animationSpec = tween(1500, easing = BounceInterpolator().toEasing())),
            exit = fadeOut(animationSpec = tween(1000)) + shrinkVertically(
                animationSpec = tween(1000, easing = BounceInterpolator().toEasing()))
        ) {
            Text(text = "Hello, world!")
        }

        Button(onClick = { visible = !visible }) {
            Text(text = "Click Me")
        }
    }
}

@Composable
fun AnimatedVisibilityViewStep6() {
    // 때때로 에니메이션 상태를 알고 싶을때..
    var visible by remember {
        mutableStateOf(true)
    }

     val state = remember {
         MutableTransitionState(false).apply {
             // Start the Animation immediately.
             targetState = true
         }
     }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visibleState = state,
            enter = fadeIn(animationSpec = tween(1000)) +
                    expandVertically(
                        animationSpec = tween(1500, easing = BounceInterpolator().toEasing())),
            exit = fadeOut(animationSpec = tween(1000)) + shrinkVertically(
                animationSpec = tween(1000, easing = BounceInterpolator().toEasing()))
        ) {
            Text(text = when {
                state.isIdle && state.currentState -> "Hello, World!"
                !state.isIdle && state.currentState -> "Disappearing"
                state.isIdle && !state.currentState -> ""
                else -> "Appearing"
            })
        }

        Button(onClick = { state.targetState = !state.targetState }) {
            Text(text = "Click Me")
        }
    }
}

@Composable
fun AnimatedVisibilityViewStep7() {
    // visibility not GONE
    var visible by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.height(20.dp)) {
            this@Column.AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(1000)) + expandVertically(
                    animationSpec = tween(1500, easing = BounceInterpolator().toEasing())
                ),
                exit = fadeOut(animationSpec = tween(1000)) + shrinkVertically(
                    animationSpec = tween(1500, easing = BounceInterpolator().toEasing())
                )
            ) {
                Text(text = "Hello, world!")
            }
        }

        Button(onClick = { visible = !visible }) {
            Text(text = "Click Me")
        }
    }
}