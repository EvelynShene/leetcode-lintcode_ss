/** 236. Swap Bits(lintcode)
 *      Write a program to swap odd and even bits in an integer with as few instructions as possible 
 *   (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).
 *
 *      Example: 5 = (101)2 => (1010)2 = 10
 */
 
 /* Method:
  *   0xaaaaaaaa = 10101010...10101010, 用x与它做与运算，可以保留x所有的偶数位的bit值;
  *   0x55555555 = 01010101...01010101, 用x与它做与运算，可以保留x所有的奇数位的bit值；
  *   因为要交换奇偶位的bit值，所以把偶数位均左移一位，奇数位均右移一位，然后在做或关系，得到的就是最后的结果值
  */
  public int swapOddEvenBits(int x) {
      return ((x & 0xaaaaaaaa) >>> 1 | (x & 0x55555555) << 1);
  }
