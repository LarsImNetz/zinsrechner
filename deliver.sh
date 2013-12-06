# scp target/dateviewer.war vgl_allgemein@solv151.hypoport.local:/opt/instances/vgl_allgemein/webapps
# scp target/dateviewer.war vgl_bv@solv168.hypoport.local:/opt/instances/vgl_bv/webapps

FINDMOON=$(L findmoon)
WARFILE=$(L warfile)
if [ -z "$WARFILE" ]; then
    echo "Kein WAR File gefunden, nichts gebaut?"
    exit 1
fi

USER=langha_l
PATHNAME=/var/lib/tomcat-7-testing/webapps
SSH_OPT=

DEBUGGING=

$DEBUGGING ssh $SSH_OPT $USER@$FINDMOON  -p 21007 "sudo /etc/init.d/tomcat-7-testing stop"
$DEBUGGING scp  -P 21007 ${WARFILE} $USER@${FINDMOON}:$PATHNAME
$DEBUGGING ssh $SSH_OPT $USER@$FINDMOON  -p 21007 "sudo /etc/init.d/tomcat-7-testing start"
