package com.picpay.desafio.android.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.data.local.DbConstants
import com.picpay.desafio.android.domain.model.User

@Entity(tableName = DbConstants.USER_TABLE_NAME)
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = DbConstants.USER_ID)
    val id: Int,
    @ColumnInfo(name = DbConstants.USER_IMG)
    val img: String,
    @ColumnInfo(name = DbConstants.USER_NAME)
    val name: String,
    @ColumnInfo(name = DbConstants.USER_USERNAME)
    val username: String
)

/*fun UserEntity.toModel() = map {
    User(it.img,it.name,it.id,it.username )
}*/
fun UserEntity.toModel(): User = User(
    id = this.id,
    name = this.name,
    img = this.img,
    username = this.username
)

