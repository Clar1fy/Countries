package com.timplifier.countries.core.base

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.timplifier.countries.core.ui.state.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseBottomSheet<Binding : ViewBinding, ViewModel : BaseViewModel>(
    private val isFullHeight: Boolean,
    @LayoutRes layoutId: Int,
) : BottomSheetDialogFragment(layoutId) {

    protected abstract val binding: Binding
    protected abstract val viewModel: ViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            setupBottomSheetBackgroundTransparent(it)
            setupDraggable(it, false)
            if (isFullHeight) {
                setupFullHeight(it)
            }
        }
        return dialog
    }

    protected fun setupBottomSheetBackgroundTransparent(dialogInterface: DialogInterface) {
        val bottomSheetDialog = dialogInterface as BottomSheetDialog
        val bottomSheet = bottomSheetDialog.findViewById<View>(
            com.google.android.material.R.id.design_bottom_sheet
        ) ?: return
        bottomSheet.setBackgroundColor(Color.TRANSPARENT)
    }

    private fun setupFullHeight(bottomSheet: DialogInterface) {
        val bottomSheetDialog = bottomSheet as BottomSheetDialog
        val parentLayout =
            bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        parentLayout?.let { it ->
            val behaviour = BottomSheetBehavior.from(it)
//            setupFullHeight(it)
            behaviour.isFitToContents = true
            behaviour.peekHeight = 2000
            behaviour.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    protected fun setupDraggable(dialogInterface: DialogInterface, isLoading: Boolean) {
        val bottomSheetDialog = dialogInterface as BottomSheetDialog
        val bottomSheet = bottomSheetDialog.findViewById<View>(
            com.google.android.material.R.id.design_bottom_sheet
        ) ?: return
        bottomSheet.setBackgroundColor(Color.TRANSPARENT)
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet)
        behavior.isDraggable = !isLoading
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        assembleViews()
        constructListeners()
        launchObservers()
    }

    protected open fun initialize() {}
    protected open fun assembleViews() {}
    protected open fun constructListeners() {}
    protected open fun launchObservers() {}


    protected fun setupOffsetTop(offsetFromTop: Int) {
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            isFitToContents = false
            expandedOffset = offsetFromTop
        }
    }

    protected fun <T> StateFlow<UIState<T>>.spectateUiState(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        success: ((data: T) -> Unit)? = null,
        loading: ((data: UIState.Loading<T>) -> Unit)? = null,
        error: ((error: String) -> Unit)? = null,
        idle: ((idle: UIState.Idle<T>) -> Unit)? = null,
        gatherIfSucceed: ((state: UIState<T>) -> Unit)? = null,
    ) {
        safeFlowGather(lifecycleState) {
            collect {
                gatherIfSucceed?.invoke(it)
                when (it) {
                    is UIState.Idle -> idle?.invoke(it)
                    is UIState.Loading -> loading?.invoke(it)
                    is UIState.Error -> error?.invoke(it.error)
                    is UIState.Success -> success?.invoke(it.data)
                }
            }
        }
    }

    private fun safeFlowGather(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        gather: suspend () -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
                gather()
            }
        }
    }
}