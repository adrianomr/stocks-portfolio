package br.com.adrianorodrigues.stocksportfolio.context;

import br.com.adrianorodrigues.stocksportfolio.context.dto.TokenDto;
import org.springframework.stereotype.Service;

@Service
public class TokenContext {

    private static final InheritableThreadLocal<TokenDto> TOKEN = new InheritableThreadLocal<>();

    public void setTokenDto(TokenDto tokenDto){
        TOKEN.set(tokenDto);
    }

    public TokenDto getTokenDto(){
        return TOKEN.get();
    }
}
