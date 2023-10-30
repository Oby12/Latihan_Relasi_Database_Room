package com.dicoding.mystudentdata

import androidx.lifecycle.LiveData
import com.dicoding.mystudentdata.database.Student
import com.dicoding.mystudentdata.database.StudentAndUniversity
import com.dicoding.mystudentdata.database.StudentDao
import com.dicoding.mystudentdata.database.StudentWithCourse
import com.dicoding.mystudentdata.database.UniversityAndStudent
import com.dicoding.mystudentdata.helper.InitialDataSource
import com.dicoding.mystudentdata.helper.SortUtils
import com.dicoding.mystudentdata.helper.SortyType

class StudentRepository(private val studentDao: StudentDao) {
    fun getAllStudent(sortyType: SortyType): LiveData<List<Student>> {
        val query = SortUtils.getSortedQuery(sortyType)
        return studentDao.getAllStudent(query)
    }

    //memangil fungsi dari studenDao  | Many TO one
    fun getAllStudentAndUniversity(): LiveData<List<StudentAndUniversity>> = studentDao.getAllStudentAndUniversity()

    //memanggil funsi dari student dao | One To Many
    fun getAllUniversityAndStudent(): LiveData<List<UniversityAndStudent>> = studentDao.getAllUniversityAndStudent()

    //mengambil fungsi dari student dao setelah kita menambhkan relasi many to many di initialDataSource | Many TO Many
    fun getAllStudentWithCourse(): LiveData<List<StudentWithCourse>> = studentDao.getAllStudentWithCourse()

//    suspend fun insertAllData() {
//        studentDao.insertStudent(InitialDataSource.getStudents())
//        studentDao.insertUniversity(InitialDataSource.getUniversities())
//        studentDao.insertCourse(InitialDataSource.getCourses())
//        studentDao.insertCourseStudentCrossRef(InitialDataSource.getCourseStudentRelation())
//    }
}