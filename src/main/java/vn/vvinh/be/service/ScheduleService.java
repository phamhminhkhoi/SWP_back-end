package vn.vvinh.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vvinh.be.dto.ScheduleRequestDTO;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.entity.Schedule;
import vn.vvinh.be.repository.AccountRepository;
import vn.vvinh.be.repository.ScheduleRepository;
import java.util.List;
@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    AccountRepository accountRepository;

    public Schedule postSchedule(ScheduleRequestDTO scheduleRequestDTO){
        Schedule schedule = new Schedule();
        Account account = accountRepository.findAccountById(scheduleRequestDTO.getAccountID());
        schedule.setAccount(account);
        account.setSchedule(schedule);
        schedule.setTimeFrom(scheduleRequestDTO.getTimeFrom());
        schedule.setTimeTo(scheduleRequestDTO.getTimeTo());
        Schedule newSchedule = scheduleRepository.save(schedule);
        return newSchedule;
    }

    public Schedule updateSchedule(ScheduleRequestDTO scheduleRequestDTO, long id){
        Schedule schedule = scheduleRepository.getScheduleById(id);
        schedule.setTimeFrom(scheduleRequestDTO.getTimeFrom());
        schedule.setTimeTo(scheduleRequestDTO.getTimeTo());
        return scheduleRepository.save(schedule);
    }

    public Schedule deleteSchedule(long id){
        Schedule schedule = scheduleRepository.getScheduleById(id);
        schedule.setDeleted(true);
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllScheduleByOneHost( long hostID){
        return  scheduleRepository.getAllScheduleByAccount(accountRepository.findAccountById(hostID));
    }

}