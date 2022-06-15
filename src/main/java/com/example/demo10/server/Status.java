package com.example.demo10.server;

public enum Status {
    CREATED, 				//= "Утверждено руководителем";
    ACCEPTED_BY_ADMINISTRATION, 				//= "Утверждено ген. директором";
    ACCEPTED_BY_LEADER,  	//= "Утверждено гл. бухгалтером как руководителем";
    REFUSED_BY_LEADER, 					//= "Отклонено руководителем";
    REFUSED_BY_ADMINISTRATION,				//= "Отклонено гл. бухгалтером";
    PAID, 						//= "Рассматривается";
    CANCELLED, 							//= "Отменено пользователем";
    MOVED, 								//= "Перенесено";
    CHANGED,
    NEEDED_TO_ADD_INFORMATION,//= "Использован";
    STARTED,
    FINISHED//= fallback;
}
