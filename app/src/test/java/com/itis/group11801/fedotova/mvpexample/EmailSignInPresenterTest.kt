package com.itis.group11801.fedotova.mvpexample

import com.itis.group11801.fedotova.mvpexample.data.repository.UserRepository
import com.itis.group11801.fedotova.mvpexample.ui.main.signin_email.EmailSignInPresenter
import com.itis.group11801.fedotova.mvpexample.ui.main.signin_email.`EmailSignInView$$State`
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class EmailSignInPresenterTest {

    private val dispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var mockViewState: `EmailSignInView$$State`

    @MockK
    lateinit var mockUserRepository: UserRepository

    lateinit var presenter: EmailSignInPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
        presenter = spyk(EmailSignInPresenter(mockUserRepository))
        presenter.setViewState(mockViewState)
    }

    @Test
    fun resetPasswordSuccess() {
        val msg = "Check email to reset password"
        val email = "email@mail.ru"
        coEvery { mockUserRepository.resetPassword(any()) } returns true
        every { mockViewState.showToast(msg) } just Runs

        presenter.resetPassword(email)

        verify { mockViewState.showToast(msg) }
    }

    @Test
    fun resetPasswordFail() {
        val msg = "Failed"
        val email = "email@mail.ru"
        coEvery { mockUserRepository.resetPassword(any()) } returns false
        every { mockViewState.showToast(msg) } just Runs

        presenter.resetPassword(email)

        verify { mockViewState.showToast(msg) }
    }

    @Test
    fun signInFail() {
        val email = "email@mail.ru"
        val password = "password"
        coEvery { mockUserRepository.signInEmail(any(), any()) } returns false
        every { mockViewState.showToast(any()) } just Runs

        presenter.signIn(email, password)

        verify { mockViewState.showToast(any()) }
    }

    @Test
    fun signInSuccess() {
        val email = "email@mail.ru"
        val password = "password"
        coEvery { (mockUserRepository.signInEmail(any(), any())) } returns true
        every { mockViewState.showToast(any()) } just Runs
        every { mockViewState.navigateToInfo() } just Runs

        presenter.signIn(email, password)

        verify { mockViewState.showToast(any()) }
        verify { mockViewState.navigateToInfo() }
    }

    @Test
    fun navigateToSignUp() {
        every { mockViewState.navigateToSignUp() } just Runs
        presenter.navigateToSignUp()
        verify { mockViewState.navigateToSignUp() }
    }
}
