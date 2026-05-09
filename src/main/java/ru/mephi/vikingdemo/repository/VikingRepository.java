/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.vikingdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mephi.vikingdemo.model.Viking;



/**
 *
 * @author march
 */
public interface VikingRepository extends JpaRepository<Viking, Long> {
}
