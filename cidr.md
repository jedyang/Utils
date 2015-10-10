### 用ip和掩码长度生成子网地址

               /**
                   *
                   * <一句话功能简述>
                   * 用ip和掩码长度生成子网地址
                   * @param ip
                   * @param maskLength
                   * @return
                   * @author yunsheng
                   */
                  public String getCidr(String ip, int maskLength)
                  {
                      int[] value = {255, 255, 255, 255};
                      int mask = 0xFFFFFFFF << (32 - maskLength);
                      for (int i = 0; i < value.length; i++)
                      {
                          value[i] = value[i] & (mask >> (i * 8));
                      }
                      String maskAddress = String.format("%d.%d.%d.%d", value[3], value[2], value[1], value[0]);
                      return subnetAddress(ip, maskAddress);
                  }
              
              
                  /**
                   *
                   * <一句话功能简述>
                   * 用ip和掩码生成子网地址
                   * @param ipAddress
                   * @param maskAddress
                   * @return
                   * @author yunsheng
                   */
                  public static String subnetAddress(String ipAddress, String maskAddress)
                  {
                      String subnetAddressValue = null;
                      ipAddress = validateAddressFormate(ipAddress);
                      maskAddress = validateAddressFormate(maskAddress);
              
                      if ((!(isTrimStrEmpty(ipAddress)))
                              && (!(isTrimStrEmpty(maskAddress))))
                      {
                          String[] ipSeg1 = ipAddress.split("\\.");
                          String[] ipSeg2 = maskAddress.split("\\.");
                          for (int i = 0; i < ipSeg1.length; ++i)
                          {
                              String itemSeg = String.valueOf(Integer.valueOf(ipSeg1[i])
                                      .intValue() & Integer.valueOf(ipSeg2[i]).intValue());
                              if (isTrimStrEmpty(subnetAddressValue))
                              {
                                  subnetAddressValue = itemSeg;
                              }
                              else
                              {
                                  subnetAddressValue = new StringBuilder()
                                          .append(subnetAddressValue).append(".")
                                          .append(itemSeg).toString();
                              }
                          }
                      }
              
                      return subnetAddressValue;
                  }
              
                  public static String validateAddressFormate(String ipAddress)
                  {
                      String ipAddressValue = null;
                      if (!(isStringEmpty(ipAddress)))
                      {
                          ipAddress = ipAddress.trim();
                          String[] ipSeg = ipAddress.split("\\.");
                          if ((4 == ipSeg.length) && (!(ipAddress.endsWith("."))))
                          {
                              try
                              {
                                  for (String item : ipSeg)
                                  {
                                      int itemValue = Integer.valueOf(item).intValue();
                                      if (!(isIntegerInRange(itemValue, 0, 255)))
                                      {
                                          ipAddressValue = null;
                                          break;
                                      }
                                      if (isTrimStrEmpty(ipAddressValue))
                                      {
                                          ipAddressValue = String.valueOf(itemValue);
                                      }
                                      else
                                      {
                                          ipAddressValue = new StringBuilder()
                                                  .append(ipAddressValue).append(".")
                                                  .append(String.valueOf(itemValue))
                                                  .toString();
                                      }
                                  }
                              }
                              catch (NumberFormatException e)
                              {
                                  ipAddressValue = null;
                              }
                          }
                      }
                      return ipAddressValue;
                  }
              
                  public static boolean isTrimStrEmpty(String source)
                  {
                      return ((null == source) || (source.trim().length() == 0));
                  }
              
                  public static boolean isStringEmpty(String source)
                  {
                      return ((null == source) || (source.length() == 0));
                  }
              
                  public static boolean isIntegerInRange(int value, int min, int max)
                  {
                      return ((value >= min) && (value <= max));
                  }
