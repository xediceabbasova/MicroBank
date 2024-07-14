package com.company.transactionservice.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "transactions")
data class Transaction @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = "",

    @Column(name = "from_account_id", nullable = false)
    val fromAccountId: String,

    @Column(name = "to_account_id", nullable = false)
    val toAccountId: String,

    @Column(name = "amount", nullable = false)
    val amount: BigDecimal,

    @Column(name = "transaction_date", nullable = false)
    val transactionDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    var status: TransactionStatus = TransactionStatus.PENDING,

    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    val currency: Currency

) {
    fun updateStatus(newStatus: TransactionStatus) {
        status = newStatus
    }
}
