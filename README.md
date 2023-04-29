<img src="./app/src/main/ic_launcher-playstore.png" width="200" height="200">

# MDParserKit Core

MDParserKit Core is a library that provides the functionality to parse markdown text and convert it into an [AnnotatedString](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/AnnotatedString) with appropriate styles.

## Installation

Add the following dependency to your app-level `build.gradle` file:

```groovy
dependencies {
    implementation 'io.github.dakshsemwal:mdparserkitcore:1.0'
}
```
```kotlin
dependencies {
    implementation("io.github.dakshsemwal:mdparserkitcore:1.0")
}
```

## Usage

To parse a markdown string and get an [AnnotatedString](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/AnnotatedString), use the `parseMarkdown` function:

```kotlin
import com.daksh.mdparserkit.core.parseMarkdown

val markdownText = "# Heading 1\n\nThis is some **bold** and *italic* text in a paragraph."

val annotatedString = parseMarkdown(markdownText)
```

The resulting `AnnotatedString` will have appropriate styles applied to the markdown syntax.

## Functionality

MDParserKit Core provides the following functions:

### `parseMarkdown`

Parses a given markdown text and converts it into an [AnnotatedString] with appropriate styles.

```kotlin
import androidx.compose.ui.text.AnnotatedString
import com.daksh.mdparserkit.core.parseMarkdown

/**
 * @param markdownText The input markdown text to parse.
 * @return An [AnnotatedString] with styles applied according to the markdown syntax.
 */
fun parseMarkdown(markdownText: String): AnnotatedString
```

### `textMarkDown`

Converts markdown-style text formatting to [AnnotatedString] with appropriate [SpanStyle]s.

```kotlin
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.TextUnit
import com.daksh.mdparserkit.core.textMarkDown

/**
 * @param inputText The input text to be converted.
 * @param resultBuilder The [AnnotatedString.Builder] to append the converted text to.
 * @param fontSize The desired font size for the text.
 * @param fontWeight The desired font weight for the text.
 * @return The converted text with markdown formatting replaced by appropriate [SpanStyle]s.
 */
private fun textMarkDown(
    inputText: String,
    resultBuilder: AnnotatedString.Builder,
    fontSize: TextUnit,
    fontWeight: FontWeight = FontWeight.Normal
)
```
