package com.payments.CaseHandler.dto;

import com.payments.CaseHandler.util.CaseStatus;
import com.payments.CaseHandler.util.CaseType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payment_case")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Payment payment;
    private CaseType type;
    private CaseStatus status;
}
