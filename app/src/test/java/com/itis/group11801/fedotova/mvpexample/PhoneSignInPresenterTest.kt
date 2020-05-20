package com.itis.group11801.fedotova.mvpexample

import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.getCredential
import com.itis.group11801.fedotova.mvpexample.data.repository.UserRepository
import com.itis.group11801.fedotova.mvpexample.ui.main.signin_phone.PhoneSignInPresenter
import com.itis.group11801.fedotova.mvpexample.ui.main.signin_phone.`PhoneSignInView$$State`
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class PhoneSignInPresenterTest {

    private val dispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var mockViewState: `PhoneSignInView$$State`

    @MockK
    lateinit var mockUserRepository: UserRepository

    @MockK
    lateinit var credential: PhoneAuthCredential

    @MockK
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    lateinit var presenter: PhoneSignInPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
        mockkStatic(PhoneAuthProvider::class)
        presenter = spyk(PhoneSignInPresenter(mockUserRepository))
        presenter.setViewState(mockViewState)
    }

    @Test
    fun verifyPhoneNumberWithCodeSuccess() {
        val code = "code"
        val id = "id"
        every { getCredential(id, code) } returns credential
        coEvery { (mockUserRepository.signInWithCredential(any())) } returns true
        every { mockViewState.showToast("Authentication success") } just Runs
        every { mockViewState.navigateToInfo() } just Runs

        presenter.verifyPhoneNumberWithCode(code)

        verify { mockViewState.showToast("Authentication success") }
        verify { mockViewState.navigateToInfo() }
    }

    @Test
    fun verifyPhoneNumberWithCodeFail() {
        val code = "code"
        val id = "id"
        every { getCredential(id, code) } returns credential
        coEvery { (mockUserRepository.signInWithCredential(any())) } returns false
        every { mockViewState.showToast("Authentication Failed") } just Runs
        every { mockViewState.navigateToInfo() } just Runs

        presenter.verifyPhoneNumberWithCode(code)

        verify { mockViewState.showToast("Authentication Failed") }
        verify { mockViewState.navigateToInfo() }
    }
}
