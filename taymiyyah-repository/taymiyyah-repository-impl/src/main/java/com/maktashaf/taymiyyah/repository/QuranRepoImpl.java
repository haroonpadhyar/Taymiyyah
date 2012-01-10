package com.maktashaf.taymiyyah.repository;

import com.maktashaf.taymiyyah.domain.Quran;
import org.springframework.stereotype.Repository;

/**
 * @author Haroon Anwar padhyar.
 */

@Repository
public class QuranRepoImpl extends GenericRepoImpl<Quran, Long> implements QuranRepo{
}
