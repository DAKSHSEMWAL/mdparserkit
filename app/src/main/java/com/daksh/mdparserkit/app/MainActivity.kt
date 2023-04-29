package com.daksh.mdparserkit.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daksh.mdparserkit.app.databinding.ActivityMainBinding
import com.daksh.mdparserkit.core.parseMarkdownUsingSpannableString

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val markdownContent = "# Heading 1\n" +
                "\n" +
                "This is a **bold** word and *this is an italic word*.\n" +
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
                "#### Heading 4\n" +
                "\n" +
                "**_This is a bold-italic word_**\n"
        binding.markdownText.text = parseMarkdownUsingSpannableString(markdownContent)
        binding.buttonAppcompose.setOnClickListener {
            val intent = Intent(it.context, ComposeActivity::class.java)
            startActivity(intent)
        }
    }
}
