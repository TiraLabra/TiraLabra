Testaustilanne 7.4.2014

JUnit testejä on toteutettu vain muutamalle luokalle: Perceptron ja Vector.

TrainingData ei sisällä tällä hetkellä juurikaan testattavaa.

MultiClassPerceptron vaatii testausta, tällä hetkellä olen kokeillut käsin samalla tavoin kuin Perceptron-luokkaa, ja se vaikuttaa toimivan ainakin binäärisessä luokittelussa. Useamman luokan tapausta tulee vielä testata kattavammin.

MLP: Tätä kokonaisuutta on hankala testata osittain satunnaisuuden takia. Tällä hetkellä MLP toteutukseni tekee suuria virheitä helpoinpienkin ongelmien ratkaisussa, ja tuntuu jäävän jumiin virhefunktion lokaaleihin minimikohtiin. Joko toteutuksessani on vakava virhe tai algoritmin toimintaa tulee parantaa pienillä hienosäädöillä. Parhaimmillaan sain onnistumisprosentin karkeasti kuuteenkymmeneen XOR:ia opetellessa (laskettu käsin käymällä läpi parikymmentä suorituskertaa).
