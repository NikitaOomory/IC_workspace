package com.example.MPM.contract;

import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Адаптер-словарь категорий розыскных дел
 */
@Component
public class AdapterCategory {

    private static Map<Integer, String> categorysHashMap = new HashMap();
    private static Map <Integer, String> sortedHashMap;

    static {
        categorysHashMap.put(121, "121 - обвиняемый, осужденный, совершивший побег из СИЗО");
        categorysHashMap.put(143, "143 - подозреваемый, обвиняемый, осужденный, совершивший побег из ИВС");
        categorysHashMap.put(187, "187 - подозреваемый, обвиняемый, осужденный, совершивший побег из-под конвоя полиции");
        categorysHashMap.put(198, "198 - обвиняемый, - осужденный, совершивший побег из-под конвоя ФСИН России");
        categorysHashMap.put(199, "199 - обвиняемый, - осужденный, совершивший побег из лечебно- исправительного учреждения");
        categorysHashMap.put(290, "290 - обвиняемый, скрывшийся от органов федеральной службы безопасности");
        categorysHashMap.put(291, "291 - подозреваемый, скрывшийся от органов федеральной службы безопасности ");
        categorysHashMap.put(293, "293 - обвиняемый, подсудимый, скрывшийся от органов дознания ФССП России");
        categorysHashMap.put(296, "296 - обвиняемый, подсудимый, скрывшийся от военного следствия");
        categorysHashMap.put(297, "297 - обвиняемый, подсудимый, скрывшийся от подразделений следствия МВД России");
        categorysHashMap.put(298, "298 - обвиняемый, подсудимый, скрывшийся от органов дознания МВД России");
        categorysHashMap.put(299, "299 - обвиняемый, подсудимый, скрывшийся от суда");
        categorysHashMap.put(300, "300 - обвиняемый, подсудимый, скрывшийся от органов СК России");
        categorysHashMap.put(350, "350 - подозреваемый, скрывшийся от следствия органов МВД России");
        categorysHashMap.put(351, "351 - подозреваемый, скрывшийся от органов дознания МВД России");
        categorysHashMap.put(352, "352 - подозреваемый, скрывшийся от органов СК России");
        categorysHashMap.put(353, "353 - подозреваемый, скрывшийся от военного следствия");
        categorysHashMap.put(354, "354 - подозреваемый, скрывшийся от органов дознания ФССП России");
        categorysHashMap.put(362, "362 - без вести пропавший сотрудник МВД России");
        categorysHashMap.put(363, "363 - без вести пропавший сотрудник прокуратуры");
        categorysHashMap.put(364, "364 - без вести пропавший сотрудник суда");
        categorysHashMap.put(365, "365 - без вести пропавший сотрудник органов ФСБ России");
        categorysHashMap.put(366, "366 - без вести пропавший сотрудник ФНС России");
        categorysHashMap.put(367, "367 - без вести пропавший сотрудник Минобороны России");
        categorysHashMap.put(368, "368 - без вести пропавший сотрудник таможенных органов");
        categorysHashMap.put(370, "370 - без вести пропавший сотрудник органов законодательной власти");
        categorysHashMap.put(371, "371 - без вести пропавший сотрудник органов исполнительной власти ");
        categorysHashMap.put(375, "375 - без вести пропавший сотрудник ФСИН России");
        categorysHashMap.put(396, "396 - без вести пропавший");
        categorysHashMap.put(401, "401 - без вести пропавший малолетний");
        categorysHashMap.put(405, "405 - без вести пропавший несовершеннолетний");
        categorysHashMap.put(410, "410 - несовершеннолетние, самовольно ушедшие из семей");
        categorysHashMap.put(411, "411 - несовершеннолетние, самовольно ушедшие из специализированных учреждений для несовершеннолетних, нуждающихся в социальной реабилитации");
        categorysHashMap.put(415, "415 - несовершеннолетние, самовольно ушедшие из специальных учебно-воспитательных учреждений закрытого типа");
        categorysHashMap.put(455, "455 - неопознанный труп");
        categorysHashMap.put(476, "476 - несовершеннолетние, ушедшие из дома, школы-интерната, детского дома, бежавшие из ЦВСНП, специальной школы, специального училища");
        categorysHashMap.put(610, "610 - лица, уклоняющиеся от исполнения назначенных им судом принудительных мер медицинского характера или принудительных мер воспитательного воздействия");
        categorysHashMap.put(615, "615 - лица, уклоняющиеся от недобровольной госпитализации, назначенной судом в связи с наличием психического расстройства");
        categorysHashMap.put(620, "620 - обвиняемый, подсудимый, скрывшийся от органов дознания МЧС России");
        categorysHashMap.put(621, "621 - подозреваемый, скрывшийся от органов дознания МЧС России");
        categorysHashMap.put(622, "622 - без вести пропавший сотрудник МЧС России");
        categorysHashMap.put(623, "623 - неизвестный, который по состоянию здоровья, возрасту или иным причинам не может сообщить о себе сведения");

        sortedHashMap = categorysHashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }


    public AdapterCategory() {
    }

    public String getStringCategoryFromInteger(Integer numberCat){
            return categorysHashMap.get(numberCat);
    }

    public Integer getIntegerCategoryFromString(String cat){
        Integer result = 0;
        for(Integer integer : categorysHashMap.keySet()){
            if(categorysHashMap.get(integer).equals(cat)){
                result = integer;
            }
        }
        return result;
    }

    public List<String> getArrayListCategorys() {
        List<String> result = new ArrayList<>();
        for(String value : sortedHashMap.values()){
            result.add(value);
        }
        return result;
    }
}
