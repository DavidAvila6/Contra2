package Objetos.Character;

public class Character2Factory implements CharacterFactory {

    @Override
    public Character createPlayerCharacter() {
        
        return new Character2(); 
            
        
    }
    
}
