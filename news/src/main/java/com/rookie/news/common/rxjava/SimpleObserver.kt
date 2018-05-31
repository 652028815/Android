package com.rookie.news.common.rxjava

import com.rookie.news.base.BaseViewModel

/**
 * Author: FK
 * Date：  2018/4/24.
 */
abstract class SimpleObserver<T>(viewModel: BaseViewModel) : NetworkObserver<T>(viewModel.networkState)