INSERT INTO public.drug(
             drug_name)
    VALUES ('Бедаквилин');
INSERT INTO public.drug(
             drug_name)
    VALUES ('Деламанид');
INSERT INTO public.drug(
             drug_name)
    VALUES ('Линезолид');
INSERT INTO public.drug(
             drug_name)
    VALUES ('Клофазимин');
INSERT INTO public.drug(
             drug_name)
    VALUES ('Пиразинамид');
INSERT INTO public.drug(
             drug_name)
    VALUES ('Этамбутол');
INSERT INTO public.drug(
             drug_name)
    VALUES ('Канамицин');
INSERT INTO public.drug(
             drug_name)
    VALUES ('Капреомицин');
INSERT INTO public.drug(
             drug_name)
    VALUES ('Амикацин');
INSERT INTO public.drug(
             drug_name)
    VALUES ('Левофлоксацин');
INSERT INTO public.drug(
             drug_name)
    VALUES ('Моксифлоксацин');
INSERT INTO public.drug(
             drug_name)
    VALUES ('Протионамид');
INSERT INTO public.drug(
             drug_name)
    VALUES ('Циклосерин');
INSERT INTO public.drug(
             drug_name)
    VALUES ('ПАСК');


INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (1, 'Неделя 2',14);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (2, 'Месяц 1',30);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (3, 'Месяц 2',60);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (4, 'Месяц 3',90);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (5, 'Месяц 4',120);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (6, 'Месяц 5',150);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (7, 'Месяц 6',180);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (8, 'Месяц 7',210);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (9, 'Месяц 8',240);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (10, 'Месяц 9',270);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (11, 'Месяц 10',300);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (12, 'Месяц 11',330);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (13, 'Месяц 12',360);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (14, 'Месяц 13',390);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (15, 'Месяц 14',420);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (16, 'Месяц 15',450);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (17, 'Месяц 16',480);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (18, 'Месяц 17',510);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (19, 'Месяц 18',540);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (20, 'Месяц 19',570);
INSERT INTO public.event_prototype(
	event_prototype_id, event_prototype_name, event_interval)
	VALUES (21, 'Месяц 20',600);

INSERT INTO public.list_date(
            factor_risk, localizatoin, releavence, clinical_form, mbt_status,
            patient_type, dst_status, administration_option, gender, role_option,
            adverse_status_option, activation_status_option, role_option_eng,
            factor_risk_eng, localizatoin_eng, releavence_eng, clinical_form_eng,
            mbt_status_eng, patient_type_eng, dst_status_eng, administration_option_eng,
            gender_eng, adverse_status_option_eng, activation_status_option_eng)
    VALUES ('Контактный', 'Легочный ТБ', 'Односторонний', 'Диссеминированный туберкулез', 'МБТ +',
            'Новый случай', 'Полирезистентность','Приём препарата под контролем','Мужчина','Координатор','Разрешено','Активный',
            'coordinator','Contact','Pulmonary TB','Unilateral',
            'Disseminated tuberculosis','MBT +','A new case','Multidrug resistance',
            'Administration a drug under control','Male','Solved','Active');


INSERT INTO public.list_date(
            factor_risk, localizatoin, releavence, clinical_form, mbt_status,
            patient_type, dst_status, administration_option, gender, role_option,
            adverse_status_option, activation_status_option, role_option_eng,
            factor_risk_eng, localizatoin_eng, releavence_eng, clinical_form_eng,
            mbt_status_eng, patient_type_eng, dst_status_eng, administration_option_eng,
            gender_eng, adverse_status_option_eng, activation_status_option_eng)
    VALUES ('Сахарный диабет', 'Внелегочный', 'Двусторонний', 'Милиарный туберкулез', 'МБТ -',
            'Неудача лечения', 'МЛУ','Выдача препарата на руки','Женщина','Доктор','Не разрешено','Не активный',
            'doctor','Diabetes','Extrapulmonary','Bilateral',
            'Miliary tuberculosis','MBT -','Failure of treatment','MDR',
            'Delivery of the drug on hand','Female','Not solved','Not active');


INSERT INTO public.list_date(
            factor_risk, localizatoin,  clinical_form,
            patient_type, dst_status, administration_option,  role_option,
            role_option_eng,
            factor_risk_eng, localizatoin_eng, clinical_form_eng,
            patient_type_eng, dst_status_eng, administration_option_eng)
    VALUES ('Наркомания', 'Сочетанный', 'Очаговый(ограниченный) туберкулёз',
            'Рецидив', 'преШЛУ(иньекционный)','Пропуск суточной дозы','Химизатор',
            'drug delivery','Addiction','Combined TB',
            'Focal (limited) tuberculosis',' Relapse','preXDR (injection)',
            'Skipping the daily dose');

INSERT INTO public.list_date(
            factor_risk,  clinical_form,
            patient_type, dst_status,
            factor_risk_eng,  clinical_form_eng,
            patient_type_eng, dst_status_eng)
    VALUES ('Злоупотребление алкоголем', 'Инфильтративный туберкулёз', 'Лечение после перерыва', 'преШЛУ(фторхинолон-R)',
            'Alcohol abuse','Infiltrative tuberculosis','Treatment after a break','preXDR(fluoroquinolone-R)');

INSERT INTO public.list_date(
            factor_risk,  clinical_form,
            patient_type, dst_status,
            factor_risk_eng,  clinical_form_eng,
            patient_type_eng, dst_status_eng)
    VALUES ('Нахождение в местах свободы за последние 2 года', 'Казеозная пневмония', 'Переведен', 'ШЛУ',
            'Being in prison for last 2 years','Caseous pneumonia','Transfered','XDR');

INSERT INTO public.list_date(
            factor_risk,  clinical_form,
            dst_status,
            factor_risk_eng, clinical_form_eng,
             dst_status_eng)
    VALUES ('ВИЧ', 'Туберкулёма', 'Другое', 'HIV',
            'Tuberculosis','Other');

INSERT INTO public.list_date(
            factor_risk,  clinical_form,
            factor_risk_eng, clinical_form_eng)
    VALUES ('Женщины в послеродовом периоде до 1 года','Кавернозный туберкулёз','Women in the puerperium up to 1 year',
            'Cavernous tuberculosis');

INSERT INTO public.list_date(
            factor_risk,  clinical_form,
            factor_risk_eng, clinical_form_eng)
    VALUES ('Беременность', 'Фиброзно - кавернозный туберкулёз', 'Pregnancy',
            'Fibrous-cavernous tuberculosis');
INSERT INTO public.list_date(
            factor_risk,  clinical_form,
            factor_risk_eng, clinical_form_eng)
    VALUES ('Другие', 'Цирротический туберкулёз', 'Other',
            'Cirrhotic tuberculosis');
INSERT INTO public.list_date(
            clinical_form,
            clinical_form_eng)
    VALUES ('Туберкулёз органов пищеварительной системы', 'Tuberculosis of the digestive system');
INSERT INTO public.list_date(
            clinical_form,
            clinical_form_eng)
    VALUES ('Туберкулёз органов мочеполовой системы', 'Tuberculosis of the urogenital system');
INSERT INTO public.list_date(
            clinical_form,
            clinical_form_eng)
    VALUES ('Туберкулёз центральной нервной системы и мозговых оболочек', 'Tuberculosis of the central nervous system and meninges');
INSERT INTO public.list_date(
            clinical_form,
            clinical_form_eng)
    VALUES ('Туберкулёз костей и суставов', 'Tuberculosis of bones and joints');
INSERT INTO public.list_date(
            clinical_form,
            clinical_form_eng)
    VALUES ('Туберкулёз кожи', 'Lupus');
INSERT INTO public.list_date(
            clinical_form,
            clinical_form_eng)
    VALUES ('Туберкулёз глаз', 'Tuberculosis of the eye');




INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (1, 'Оценка основных жизненно-важных признаков');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (2, 'Оценка функционального состояния');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (3, 'Краткий осмотр на периферическая нейропатия');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (4, 'Аудиометрия');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (5, 'Проверка остроты зрения и нарушение цветоощущения');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (6, 'Консультация по заврешению лечения');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (7, 'Оценка и наблюдение за побочными реакциями');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (8, 'Вес');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (9, 'Микроскопия мазка');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (10, 'Посев');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (11, 'Xpert MTB/RIF');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (12, 'Hain GenoType MTBDRsl (в некоторых учреждениях)');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (13, 'ТЛЧ 1-го ряда методом посева');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (14, 'ТЛЧ 2-го ряда методом посева');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (15, 'ЭКГ');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (16, 'Общий анализ крови');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (17, 'Мочевина, креатинин');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (18, 'Сывороточные электролиты (K, Ca)');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (19, 'Функциональные пробы печени (AST, ALT, билирубин)');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (20, 'ТТГ');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (21, 'Сывороточный альбумин');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (22, 'Антиген вируса гепатита Bs');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (23, 'Антитела к антигенам вируса гепатита C');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (24, 'Тест на беременность');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (25, 'ВИЧ-серостатус');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (26, 'Уровень клеток CD4 (каждые 6 месяцев у ВИЧ+)');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (27, 'Вирусная нагрузка (каждые 6 месяцев у ВИЧ+)');
INSERT INTO public.task_prototype(
	task_prototype_id, task_name)
	VALUES (28, 'Рентгенография органов грудной клетки');


INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (1, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (1, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (1, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (1, 15);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (1, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (2, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (2, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (2, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (2, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (2, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (2, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (2, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (2, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (2, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (2, 15);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (2, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (2, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (2, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (2, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 15);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (3, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 15);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 20);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (4, 28);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 15);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (5, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 15);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (6, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 15);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (7, 28);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 20);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (8, 28);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (9, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (10, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 20);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (11, 28);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (12, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (13, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 20);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (14, 28);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (15, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (16, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 20);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (17, 28);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (18, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (19, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 19);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 20);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (20, 28);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 1);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 2);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 3);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 4);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 5);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 7);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 8);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 9);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 10);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 12);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 13);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 14);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 16);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 17);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 18);
INSERT INTO public.protocol_events_tasks(
            event_prototype_id, task_prototype_id)
    VALUES (21, 19);

INSERT INTO public.staff(
            name, surname, phone_number, password, role)
    VALUES ('John', 'Smith', '0', 'cfcd208495d565ef66e7dff9f98764da', 'coordinator');

INSERT INTO public.staff(
            name, surname, phone_number, password, role)
    VALUES ('Doctor', 'Test', '9', '45c48cce2e2d7fbdea1afc51c7c6ad26', 'doctor');