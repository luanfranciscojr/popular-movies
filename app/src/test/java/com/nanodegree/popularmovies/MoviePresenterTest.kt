package com.nanodegree.popularmovies

import com.nanodegree.popularmovies.movies.presenter.MoviePresenter
import com.nanodegree.popularmovies.movies.view.MovieView
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class MoviePresenterTest {
    private lateinit var presenter: MoviePresenter
    @Mock
    lateinit var view: MovieView
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val retrofit:Retrofit = Retrofit.Builder().baseUrl("http://mock.teste")
                .build()
        presenter =  MoviePresenter(retrofit,view)
    }
    @Test
    @Throws(Exception::class)
    fun nextPage_isCorrect() {
        //Given
        presenter.currentPage =1
        //when
        presenter.loadNextPage()
        //then
        assertEquals(presenter.currentPage,2)
    }

    @Test
    @Throws(Exception::class)
    fun nottifyLoadingWhenTheServiceFails() {
        //when
        presenter.onFailure(null,null)
        //then
        Assert.assertTrue(presenter.isLoading)
    }

    @Test
    @Throws(Exception::class)
    fun hideProgressWhenTheServiceFailsAndFirstPage() {
        //Given
        presenter.currentPage=1
        //when
        presenter.onFailure(null,null)
        //then
        Mockito.verify(view).hideProgress()
    }


}