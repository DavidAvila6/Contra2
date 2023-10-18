package Objetos.Character;

public class Character1Factory implements CharacterFactory {

    @Override
    public Character createPlayerCharacter() {
        
        return new Character1();
    }
    
}
