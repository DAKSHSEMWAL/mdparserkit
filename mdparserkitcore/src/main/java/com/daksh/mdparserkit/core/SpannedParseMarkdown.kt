package com.daksh.mdparserkit.core

import android.graphics.Paint
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.TextPaint
import android.text.style.MetricAffectingSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan

/**
 * Parses the given markdown text and returns a [SpannableStringBuilder] containing formatted text.
 *
 * @param markdownText the markdown text to parse.
 * @return the formatted text as a SpannableStringBuilder.
 */
fun parseMarkdownUsingSpannableString(markdownText: String): SpannableStringBuilder {
    val lines = markdownText.split("\n")
    val resultBuilder = SpannableStringBuilder()
    var currentStyle: StyleSpan

    lines.forEach { line ->
        when {
            // Heading 1: Extracting content, applying bold style, and appending to resultBuilder
            line.startsWith("# ") -> {
                val inputText = line.removePrefix("# ").trim()
                textMarkDown(
                    inputText = inputText,
                    resultBuilder = resultBuilder,
                    textRatio = 1.714f,
                    bold = true
                )
            }
            // Similar processing for Heading 2 to Heading 6
            // Heading 2
            line.startsWith("## ") -> {
                val inputText = line.removePrefix("## ").trim()
                textMarkDown(
                    inputText = inputText,
                    resultBuilder = resultBuilder,
                    textRatio = 1.64f,
                    bold = true
                )
            }
            // Heading 3
            line.startsWith("### ") -> {
                val inputText = line.removePrefix("### ").trim()
                textMarkDown(
                    inputText = inputText,
                    resultBuilder = resultBuilder,
                    textRatio = 1.2857f,
                    bold = true
                )
            }
            // Heading 4
            line.startsWith("#### ") -> {
                val inputText = line.removePrefix("#### ").trim()
                textMarkDown(
                    inputText = inputText,
                    resultBuilder = resultBuilder,
                    textRatio = 1.142857f,
                    bold = true
                )
            }
            // Heading 5
            line.startsWith("##### ") -> {
                val inputText = line.removePrefix("##### ").trim()
                textMarkDown(
                    inputText = inputText,
                    resultBuilder = resultBuilder,
                    textRatio = 1f,
                    bold = true
                )
            }
            // Heading 6
            line.startsWith("###### ") -> {
                val inputText = line.removePrefix("###### ").trim()
                textMarkDown(
                    inputText = inputText,
                    resultBuilder = resultBuilder, textRatio = 0.8571f
                )
            }
            // Unordered list item: Extracting content, applying bold style, appending bullet point symbol, and appending to resultBuilder
            line.startsWith("* ") || line.startsWith("- ") -> {
                val inputText = line.removePrefix("* ").removePrefix("- ").trim()
                currentStyle = StyleSpan(Typeface.BOLD)
                resultBuilder.append("• ", currentStyle, SPAN_EXCLUSIVE_EXCLUSIVE)
                textMarkDown(
                    inputText = inputText,
                    resultBuilder = resultBuilder, textRatio = 1f
                )
            }
            // Ordered list item: Extracting content, applying bold style, appending number and period, and appending to resultBuilder
            line.matches(Regex("^\\d+\\.\\s.*$")) -> {
                val regex = Regex("^\\d+\\.\\s.*$")
                val startIndex = regex.find(line)?.range?.first ?: 0
                currentStyle = StyleSpan(Typeface.BOLD)
                if (startIndex > 0) {
                    resultBuilder.append(
                        line.substring(0, startIndex),
                        currentStyle,
                        SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                resultBuilder.append(
                    line.substring(startIndex, startIndex + 2),
                    currentStyle,
                    SPAN_EXCLUSIVE_EXCLUSIVE
                )
                textMarkDown(
                    inputText = line.substring(startIndex + 2, line.length),
                    resultBuilder = resultBuilder,
                    textRatio = 1f,
                )
            }
            // Remaining Text
            else -> {
                textMarkDown(line, resultBuilder, textRatio = 1f, true)
            }
        } // Appending new line
        resultBuilder.append("\n")
    }
    return resultBuilder.trim() as SpannableStringBuilder
}

/**
 * Parses a string of text formatted with markdown syntax and applies styles to it.
 *
 * @param inputText The input string of text to parse.
 * @param resultBuilder A mutable SpannableStringBuilder to build the styled text.
 * @param textRatio The relative size of the text.
 * @param bold A boolean indicating whether the entire text should be bolded.
 */
fun textMarkDown(
    inputText: String,
    resultBuilder: SpannableStringBuilder,
    textRatio: Float = 1f,
    bold: Boolean = false
) {
    val boldItalicPattern = Regex("\\*\\*[*_](.*?)[*_]\\*\\*")
    val boldPattern = Regex("\\*\\*(.*?)\\*\\*")
    val italicPattern = Regex("[*_](.*?)[*_]")
    val strikethroughPattern = Regex("~~(.+?)~~")

    var currentIndex = 0

    while (currentIndex < inputText.length) {
        val nextBoldItalic = boldItalicPattern.find(inputText, startIndex = currentIndex)
        val nextBold = boldPattern.find(inputText, startIndex = currentIndex)
        val nextItalic = italicPattern.find(inputText, startIndex = currentIndex)
        val nextStrikethrough = strikethroughPattern.find(inputText, startIndex = currentIndex)

        val nextMarkDown = listOfNotNull(
            nextBoldItalic,
            nextBold,
            nextItalic,
            nextStrikethrough
        ).minByOrNull { it.range.first }

        if (nextMarkDown != null) {
            if (nextMarkDown.range.first > currentIndex) {
                // Append any normal text before the markdown
                val normalText = inputText.substring(currentIndex, nextMarkDown.range.first)
                val style = CustomTagSpan(
                    isBold = false, isItalic = false
                )
                resultBuilder.append(normalText, style, SPAN_EXCLUSIVE_EXCLUSIVE)
                resultBuilder.setSpan(
                    RelativeSizeSpan(textRatio),
                    resultBuilder.length - normalText.length,
                    resultBuilder.length,
                    SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            val matchText = nextMarkDown.groupValues.getOrNull(1) ?: ""

            val style = when (nextMarkDown) {
                nextBoldItalic -> CustomTagSpan(
                    isBold = true,
                    isItalic = true
                )

                nextBold -> CustomTagSpan(
                    isBold = true
                )

                nextItalic -> CustomTagSpan(
                    isItalic = true
                )

                nextStrikethrough -> CustomTagSpan(
                    isStrikeThrough = true
                )

                else -> CustomTagSpan()
            }

            resultBuilder.append(matchText, style, SPAN_EXCLUSIVE_EXCLUSIVE)
            resultBuilder.setSpan(
                RelativeSizeSpan(textRatio),
                resultBuilder.length - matchText.length,
                resultBuilder.length,
                SPAN_EXCLUSIVE_EXCLUSIVE
            )

            currentIndex = nextMarkDown.range.last + 1
        } else {
            // Append any remaining text if no more markdown found
            val normalText = inputText.substring(currentIndex)
            val style = CustomTagSpan(
                isBold = bold
            )
            resultBuilder.append(normalText, style, SPAN_EXCLUSIVE_EXCLUSIVE)
            resultBuilder.setSpan(
                RelativeSizeSpan(textRatio),
                resultBuilder.length - normalText.length,
                resultBuilder.length,
                SPAN_EXCLUSIVE_EXCLUSIVE
            )
            currentIndex = inputText.length
        }
    }
}

/**
 * A custom span that can be used with TextView to apply bold, italic, and strike-through formatting to text.

 * @property isBold A Boolean indicating whether the span should apply bold formatting to the text.
 * @property isItalic A Boolean indicating whether the span should apply italic formatting to the text.
 * @property isStrikeThrough A Boolean indicating whether the span should apply strike-through formatting to the text.
 */
class CustomTagSpan(
    private val isBold: Boolean = false,
    private val isItalic: Boolean = false,
    private val isStrikeThrough: Boolean = false
) : MetricAffectingSpan() {

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTag(paint)
    }

    override fun updateDrawState(tp: TextPaint) {
        applyCustomTag(tp)
    }

    private fun applyCustomTag(paint: TextPaint) {
        paint.apply {
            val createTypeface = when {
                isBold && isItalic -> Typeface.BOLD_ITALIC
                isBold -> Typeface.BOLD
                isItalic -> Typeface.ITALIC
                else -> Typeface.NORMAL
            }
            typeface = Typeface.create(Typeface.DEFAULT, createTypeface)
            if (isStrikeThrough) {
                flags = flags or Paint.STRIKE_THRU_TEXT_FLAG
            }
        }
    }
}
