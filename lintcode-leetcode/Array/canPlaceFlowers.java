public boolean canPlaceFlowers(int[] flowerbed, int n) {
    int availble = 0;
    boolean f = false;
    boolean has1 = false;
    int zeron = 0;

    for(int i = 0; i < flowerbed.length; i++){
        if(flowerbed[i] == 0){
            zeron ++;
            if(i == 0){
                f= true;
            }
        }
        if(flowerbed[i] == 1){
            has1 = true;
            if(f){
                availble = availble + zeron/2;
                f = false;
            }
            else{
                availble = availble + (zeron-1)/2;
            }
            zeron = 0;
        }
    }
    if(zeron != 0){
        if(has1){
            availble = availble + zeron/2;
        }
        else{
            availble = availble + (zeron+1)/2;
        }
    }

    if(availble >= n)
        return true;
    else
        return false;
}
