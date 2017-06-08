package com.epam.adsm.action;


import com.epam.adsm.action.implementation.get.*;
import com.epam.adsm.action.implementation.post.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.epam.adsm.action.ActionConstants.*;

/**
 * Created by akmatleu on 17.05.17.
 */
public class ActionFactory {
    private  Map<String,Action> actionMap;

    private void init() {
        actionMap = new HashMap<>();
        actionMap.put("GET/login", new ShowPageAction(LOGIN_PAGE));
        actionMap.put("GET/createStaff", new ShowCreateStaffAction());
        actionMap.put("GET/homePage", new ShowPageAction(HOME_PAGE));
        actionMap.put("GET/createPatient",new CreatePatientAction());
        actionMap.put("GET/getStaffList",new GetStaffListAction());
        actionMap.put("GET/staff/edit",new ShowStaffEditAction());
        actionMap.put("GET/staffEdit",new ShowPageAction(STAFF_EDIT_PAGE));
        actionMap.put("GET/patient/edit",new ShowPatientEditAction());
        actionMap.put("GET/getPatientList",new GetPatientListAction());
        actionMap.put("GET/createReceipt", new ShowRecieptAction());
        actionMap.put("GET/createDrugAdministration",new ShowPageAction(DRUG_ADMINISTRATION_PAGE));
        actionMap.put("GET/createAdministration/drug",new ShowDrugAdminAction());
        actionMap.put("GET/createAdverseEvent/create",new ShowPageAction(ADVERSE_EVENT_PAGE));
        actionMap.put("GET/createAdverseEvent",new ShowPageAction(ADVERSE_EVENT_PAGE));
        actionMap.put("GET/patientHomePage",new ShowPatientHomeAction());
        actionMap.put("GET/patientEventDetails",new ShowPageAction(DETAIL_EVENT_PAGE));
        actionMap.put("GET/event/details", new ShowEventDetails());
        actionMap.put("GET/doctorSchedule",new ShowDoctorScheduleAction());
        actionMap.put("GET/getMyPatients",new GetPatientListAction());
        actionMap.put("GET/getMyPatientsAdverse",new ShowDoctorPatientsAdverseAction());
        actionMap.put("GET/set-languageLocale",new LocaleLanguageAction());
        actionMap.put("GET/staff/set-languageLocale",new LocaleLanguageAction());

        actionMap.put("POST/createStaff", new CreateStaffAction());
        actionMap.put("POST/login", new SignInAction());
        actionMap.put("POST/signOut",new SignOutAction());
        actionMap.put("POST/createPatient",new CreateResearchAction());
        actionMap.put("POST/staff/edit",new EditStaffAction());
        actionMap.put("POST/patient/edit",new EditPatientAcion());
        actionMap.put("POST/createReceipt",new CreateReceiptAction());
        actionMap.put("POST/createAdministration/drug",new CreateDrugAdministrationAction());
        actionMap.put("POST/homePage",new FindPatientAction());
        actionMap.put("POST/createAdverseEvent/create",new CreateAdverseEventAction());
        actionMap.put("POST/event/details",new ChangeProgressAction());
        actionMap.put("POST/getMyPatientsAdverse",new ChangeAdverseEventStatusAction());
    }

    public Action getAction(HttpServletRequest request){
        if (actionMap == null) {
            init();
        }
        return actionMap.get(request.getMethod()+request.getPathInfo());
    }
}
