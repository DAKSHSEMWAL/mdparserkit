package com.daksh.mdparserkit.core

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan

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