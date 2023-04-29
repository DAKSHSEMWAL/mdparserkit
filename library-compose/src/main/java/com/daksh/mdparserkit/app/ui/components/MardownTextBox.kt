package com.daksh.mdparserkit.app.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.daksh.mdparserkit.core.parseMarkdown


@Composable
fun MarkdownTextBox(markdown: String) {
    Text(
        text = parseMarkdown(markdown),
        modifier = Modifier.fillMaxWidth()
    )
}

@ExperimentalAnimationApi
@Preview
@Composable
fun MarkDownTextBoxPreview() {
    MaterialTheme {
        MarkdownTextBox(
            "# Heading 1\n" +
                    "\n" +
                    "This is a **bold** word and *this is an italic word*.\n" +
                    "\n" +
                    "~~This~~ is a strikethrough ~~text~~\n" +
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
                    "#### Heading 4\n" +
                    "\n" +
                    "**_This is a bold-italic word_**\n"
        )
    }
}
