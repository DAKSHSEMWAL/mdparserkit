package com.daksh.mdparserkit.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daksh.mdparserkit.app.ui.components.MarkdownTextBox

class ComposeActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppMain()
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun AppMain() {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Kotlin Android Template") },
                    backgroundColor = MaterialTheme.colors.primary
                )
            },
            backgroundColor = MaterialTheme.colors.background
        ) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
                    .padding(horizontal = 8.dp)
            ) {
                MarkdownTextBox("# Heading 1\n" +
                        "\n" +
                        "This is a **bold** word and *this* is an _italic_ word.\n" +
                        "\n" +
                        "~~This is a strikethrough text~~\n" +
                        "\n" +
                        "## Heading 2\n" +
                        "\n" +
                        "- This is a bullet point\n" +
                        "- Another bullet point\n" +
                        "- Yet another bullet point\n" +
                        "\n" +
                        "### Heading 3\n" +
                        "\n" +
                        "1. This is a numbered list item\n" +
                        "2. Another numbered list item\n" +
                        "3. Yet another numbered list item\n" +
                        "\n" +
                        "**Heading**:This is a pragraph heading\n" +
                        "#### Heading 4\n" +
                        "\n" +
                        "**_This is a bold-italic word_**\n")
            }
        }
    }
}
