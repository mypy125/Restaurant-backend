package com.mygitgor.tacocloud.service.impl;

import com.mygitgor.tacocloud.repository.interfaces.TacoRepository;
import com.mygitgor.tacocloud.service.TacoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TacoServiceImpl implements TacoService {
    private final TacoRepository tacoRepository;


}
