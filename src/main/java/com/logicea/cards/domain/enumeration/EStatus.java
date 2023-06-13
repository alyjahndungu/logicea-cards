package com.logicea.cards.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EStatus {
    TO_DO ("TO_DO"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE ("DONE");

    private  final String status;
}
