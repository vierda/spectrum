package com.spectrum.demo.specapp.member.data

import com.spectrum.demo.specapp.common.data.model.Member
import com.spectrum.demo.specapp.common.util.DefaultSubscriber
import com.spectrum.demo.specapp.common.util.UseCase

class MemberUseCase : UseCase() {

    private var repository: MemberRepository = MemberRepository()

    fun getAllMembers(companyId: String, subscriber: DefaultSubscriber<List<Member>>) {
        execute(repository.selectAllMemberFromDb(companyId), subscriber)
    }

    fun updateFavorite(isFavorite: Boolean, companyId: String, subscriber: DefaultSubscriber<Boolean>) {
        execute(repository.updateFavorite(isFavorite, companyId), subscriber)
    }
}