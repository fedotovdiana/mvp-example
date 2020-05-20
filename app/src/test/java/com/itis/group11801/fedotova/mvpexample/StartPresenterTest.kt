package com.itis.group11801.fedotova.mvpexample

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.GoogleAuthProvider.getCredential
import com.itis.group11801.fedotova.mvpexample.data.repository.UserRepository
import com.itis.group11801.fedotova.mvpexample.ui.main.start_signin_google.StartPresenter
import com.itis.group11801.fedotova.mvpexample.ui.main.start_signin_google.`StartView$$State`
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class StartPresenterTest {

    private val dispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var mockViewState: `StartView$$State`

    @MockK
    lateinit var mockUserRepository: UserRepository

    @MockK
    lateinit var authCredential: AuthCredential

    lateinit var presenter: StartPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
        presenter = spyk(StartPresenter(mockUserRepository))
        presenter.setViewState(mockViewState)
    }

    @Test
    fun firebaseAuthWithGoogle() {
        val token = "token"
        mockkStatic(GoogleAuthProvider::class)
        every { getCredential(token, null) } returns authCredential
        coEvery { (mockUserRepository.signInWithCredential(any())) } returns true

        presenter.firebaseAuthWithGoogle(token)

        verify { mockViewState.navigateToInfo() }
    }

    @Test
    fun onException() {
        every { mockViewState.showToast(any()) } just Runs
        presenter.onException()
        verify { mockViewState.showToast(any()) }
    }

    @Test
    fun signIn() {
        every { mockViewState.signIn() } just Runs
        presenter.signIn()
        verify { mockViewState.signIn() }
    }

    @Test
    fun navigateToInfo() {
        every { mockViewState.navigateToInfo() } just Runs
        presenter.navigateToInfo()
        verify { mockViewState.navigateToInfo() }
    }

    @Test
    fun navigateToEmail() {
        every { mockViewState.navigateToEmail() } just Runs
        presenter.navigateToEmail()
        verify { mockViewState.navigateToEmail() }
    }

    @Test
    fun navigateToPhone() {
        every { mockViewState.navigateToPhone() } just Runs
        presenter.navigateToPhone()
        verify { mockViewState.navigateToPhone() }
    }
}
