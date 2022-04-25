package com.levelabdev.dictionary.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.levelabdev.dictionary.presentation.ui.WordInfoItem
import com.levelabdev.dictionary.presentation.ui.theme.DictionaryTheme
import com.levelabdev.dictionary.presentation.viewmodel.DictionaryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DictionaryTheme {
                val viewModel:DictionaryViewModel = hiltViewModel()
                val state = viewModel.state.value
                val scaffoldState = rememberScaffoldState()

                //Effect
                LaunchedEffect(key1 = true){
                    viewModel.eventFlow.collectLatest { event ->
                        when(event){
                            is DictionaryViewModel.UIEvent.ShowSnackbar ->{
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                        }
                    }
                }

                Scaffold(scaffoldState = scaffoldState) {
                    Box(modifier = Modifier.background(MaterialTheme.colors.background)){
                        Column(modifier = Modifier.fillMaxSize().padding(12.dp)){
                            TextField(
                                value = viewModel.searchQuery.value,
                                onValueChange = viewModel::onSearch,
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = {
                                    Text(text="Buscar palabra...")
                                }
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            LazyColumn(modifier = Modifier.fillMaxSize()){
                                items(state.wordInfoItems.size){ i ->
                                    val wordInfo = state.wordInfoItems[i]

                                    if(i > 0){
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }

                                    WordInfoItem(wordInfo = wordInfo)

                                    if(i<state.wordInfoItems.size -1){
                                        Divider()
                                    }
                                }
                            }
                        }
                    }
                    if(state.isLoading){
                        CircularProgressIndicator(
                            modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}
