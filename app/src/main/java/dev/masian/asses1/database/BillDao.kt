package dev.masian.asses1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dev.masian.asses1.model.Bill

@Dao
interface BillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBill(bill: Bill)

}