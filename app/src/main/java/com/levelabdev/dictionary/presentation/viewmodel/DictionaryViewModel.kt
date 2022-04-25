package com.levelabdev.dictionary.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levelabdev.dictionary.domain.model.Result
import com.levelabdev.dictionary.domain.usecase.GetWordInfoUseCase
import com.levelabdev.dictionary.presentation.state.DictionaryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    private val getWordInfo:GetWordInfoUseCase
):ViewModel(){
    //query state
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    //wordinfo state
    private val _state = mutableStateOf(DictionaryState())
    val state:State<DictionaryState> = _state

    //
    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query:String){
        _searchQuery.value = query

        searchJob?.cancel()
        searchJob = viewModelScope.launch{

            //
            getWordInfo(query).onEach { result->
                when(result){
                    is Result.Loading ->{
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                    is Result.Success ->{
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Result.Failure ->{
                        _state.value = state.value.copy(
                            wordInfoItems = emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.ShowSnackbar(
                            result.throwable.message ?: "Unexpected Error"
                        ))
                    }
                }
            }
        }
    }

    sealed class UIEvent{
        data class ShowSnackbar(val message:String):UIEvent()
    }
}