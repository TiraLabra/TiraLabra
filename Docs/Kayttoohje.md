# Käyttöohje

`mahjong-hand` binäärillä voit laskea minkä vain mahjong-käden shanten-arvon tai
muodostaa odotuspuun.

Minkä vain käden shanten-arvo: `mahjong-hand shanten <tiles>`.
Esimerkki: 

> mahjong-hand shanten M1 M1 M1 P3 P3 P3 S5 S6 S7 W W W S
> Shanten: 0

Negatiivinen tulos tarkoittaa valmista kättä ja 0 tenpai-kättä.

Odotuspuun tulostaminen: `mahjong gwt <tiles>`.
Esimerkki:

> mahjong-hand gwt M1 M1 P1 P1 S5 S5 S5 W W W S S S
> M1 M1, P1 P1, S5 S5 S5, S  S  S , W  W  W 
> └╼ Tenpai for M1 at 0 fu
> └╼ Tenpai for P1 at 0 fu 
