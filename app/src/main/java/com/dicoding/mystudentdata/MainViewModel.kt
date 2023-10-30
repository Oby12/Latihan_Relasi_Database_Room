package com.dicoding.mystudentdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.dicoding.mystudentdata.database.Student
import com.dicoding.mystudentdata.database.StudentAndUniversity
import com.dicoding.mystudentdata.database.StudentWithCourse
import com.dicoding.mystudentdata.database.UniversityAndStudent
import com.dicoding.mystudentdata.helper.SortyType
import kotlinx.coroutines.launch

class MainViewModel(private val studentRepository: StudentRepository) : ViewModel() {

    private val _sort = MutableLiveData<SortyType>()

//    init {
//        insertAllData()
//    }

    init {
        _sort.value = SortyType.ASCENDING
    }
    fun changeSortType(sortType: SortyType) {
        _sort.value = sortType
    }

    fun getAllStudent(): LiveData<List<Student>> = _sort.switchMap {
        studentRepository.getAllStudent(it)
    }

    //panggil fungsi many to one dari repository
    fun getAllStudentAndUniversity(): LiveData<List<StudentAndUniversity>> = studentRepository.getAllStudentAndUniversity()

    //pangil fungsi one to many dari repository
    fun getAllUniversityAndStudent(): LiveData<List<UniversityAndStudent>> = studentRepository.getAllUniversityAndStudent()

    //poanggil fungsi Many TO Many dari repository
    fun getAllStudentWithCourse(): LiveData<List<StudentWithCourse>> = studentRepository.getAllStudentWithCourse()

//    private fun insertAllData() = viewModelScope.launch {
//        studentRepository.insertAllData()
//    }
}

class ViewModelFactory(private val repository: StudentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}