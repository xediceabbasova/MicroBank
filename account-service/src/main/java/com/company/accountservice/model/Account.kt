package com.company.accountservice.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "accounts")
data class Account @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = "",

    @Column(name = "user_id", nullable = false)
    val userId: String,

    @Column(name = "account_number", unique = true, nullable = false)
    val accountNumber: String = generateAccountNumber(),

    @Column(name = "balance", nullable = false)
    val balance: BigDecimal = BigDecimal.ZERO,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "is_active", nullable = false)
    val isActive: Boolean = true

) {
    companion object {
        fun generateAccountNumber(): String {
            return "ACCT-" + (100000 + (Math.random() * 900000).toInt()).toString()
        }
    }
}
