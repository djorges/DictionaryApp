package com.levelabdev.dictionary.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.levelabdev.dictionary.domain.model.WordInfoModel

@Composable
fun WordInfoItem(
    wordInfo: WordInfoModel,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier){
        //Word
        Text(
            text = wordInfo.word,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Cursive,
            color = Color.Black
        )
        //Phonetic
        Text(
            text = wordInfo.word,
            fontWeight = FontWeight.W200,
        )
        Spacer( modifier = Modifier.height(16.dp))
        //Origin
        Text(text = wordInfo.origin)

        //Meanings
        wordInfo.meanings.forEach{
            //
            Text(
                text= it.partOfSpeech,
                fontWeight = FontWeight.Bold
            )
            //Definitions
            it.definitions.forEachIndexed { i, definition ->
                Text(text="${i + 1}. ${definition.definition}")//Index
                Spacer(modifier = Modifier.height(8.dp))
                definition.example?.let{ example ->Text(text="Example: $example")}
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}