# These db scripts are meant to be run on mysql 5.1 or latter.
# Data for these scripts is obtained from Tanzil.net below is the license info.

# PLEASE DO NOT REMOVE OR CHANGE THIS COPYRIGHT BLOCK
#====================================================================
#
#  Tanzil Quran Text (Simple, version 1.0.2)
#  Copyright (C) 2008-2010 Tanzil.net
#  License: Creative Commons Attribution 3.0
#
#  This copy of quran text is carefully produced, highly 
#  verified and continuously monitored by a group of specialists 
#  at Tanzil project.
#
#  TERMS OF USE:
#
#  - Permission is granted to copy and distribute verbatim copies 
#    of this text, but CHANGING IT IS NOT ALLOWED.
#
#  - This quran text can be used in any website or application, 
#    provided its source (Tanzil.net) is clearly indicated, and 
#    a link is made to http://tanzil.net to enable users to keep
#    track of changes.
#
#  - This copyright notice shall be included in all verbatim copies 
#    of the text, and shall be reproduced appropriately in all files 
#    derived from or containing substantial portion of this text.
#
#  Please check updates at: http://tanzil.net/updates/
# 
#====================================================================


Sequence to run script:
## create required schema.
1- schema.sql 

## insert quran original text in to db.
2- quran-original_book.sql

## insert urdu translation of quran by maulana maududi.
3- quran_ur_maududi.sql

## insert english translation of quran by yousufali.
3- quran_en_yousufali.sql