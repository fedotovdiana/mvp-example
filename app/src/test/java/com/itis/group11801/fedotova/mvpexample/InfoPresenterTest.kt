package com.itis.group11801.fedotova.mvpexample

import com.google.firebase.auth.FirebaseUser
import com.itis.group11801.fedotova.mvpexample.data.repository.InfoRepository
import com.itis.group11801.fedotova.mvpexample.data.repository.UserRepository
import com.itis.group11801.fedotova.mvpexample.ui.main.info.InfoPresenter
import com.itis.group11801.fedotova.mvpexample.ui.main.info.InfoView
import com.itis.group11801.fedotova.mvpexample.ui.main.info.`InfoView$$State`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class InfoPresenterTest {

    @Mock
    lateinit var mockViewState: `InfoView$$State`

    @Mock
    lateinit var mockUserRepository: UserRepository

    @Mock
    lateinit var mockInfoRepository: InfoRepository

    @Mock
    lateinit var mockCurrentUser: FirebaseUser

    @InjectMocks
    @Spy
    lateinit var presenter: InfoPresenter

    @Before
    fun setUp() {
        presenter = spy(InfoPresenter(mockUserRepository, mockInfoRepository))
        presenter.setViewState(mockViewState)
        `when`(mockUserRepository.getCurrentUser()).thenReturn(mockCurrentUser)
    }

    @Test
    fun onFirstViewAttach() {
        val mockView = mock(InfoView::class.java)
        presenter.attachView(mockView)
        verify(mockViewState).navigateToStart()
    }

    @Test
    fun crash() {
        presenter.crash()
        verify(mockUserRepository).crash()
    }

    @Test
    fun inflateAddDialog() {
        presenter.inflateAddDialog()
        verify(mockViewState).inflateAddDialog()
    }

    @Test
    fun signOut() {
        presenter.signOut()
        verify(mockUserRepository).signOut()
        verify(mockViewState).navigateToStart()
    }
}
