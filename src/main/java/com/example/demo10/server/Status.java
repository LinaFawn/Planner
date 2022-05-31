package com.example.demo10.server;

public enum Status {
    ACCEPTED_BY_LEADER, 				//= "Утверждено руководителем";
    ACCEPTED_BY_DIRECTOR, 				//= "Утверждено ген. директором";
    ACCEPTED_BY_ACCOUNTANT, 			//= "Утверждено гл. бухгалтером";
    ACCEPTED_BY_ACCOUNTANT_AS_LEADER, 	//= "Утверждено гл. бухгалтером как руководителем";
    REFUSED_BY_LEADER, 					//= "Отклонено руководителем";
    REFUSED_BY_DIRECTOR, 				//= "Отклонено ген. директором";
    REFUSED_BY_ACCOUNTANT, 				//= "Отклонено гл. бухгалтером";
    CONSIDERED, 						//= "Рассматривается";
    CANCELLED, 							//= "Отменено пользователем";
    MOVED, 								//= "Перенесено";
    IN_VACATION, 						//= "В отпуске";
    DONE, 								//= "Использован";
    WRONG								//= fallback;
}
