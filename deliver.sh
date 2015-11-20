#!/bin/bash
# -*- script -*-
#
# Deliver Script
#

echo "PLEASE FIX"
exit

FINDMOON=$(L findmoon)
# IN DELIVER
# if [ -z "$FINDMOON" ]; then
#     echo "Moonserver nicht gefunden. Abbruch!"
#     exit 1
# fi

WARFILE=$(L warfile)
# IN DELIVER
# if [ -z "$WARFILE" ]; then
#     echo "Kein WAR File gefunden, nichts gebaut?"
#     exit 1
# fi

L deliver $WARFILE to $FINDMOON for $USER
