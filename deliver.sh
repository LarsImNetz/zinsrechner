# scp target/dateviewer.war vgl_allgemein@solv151.hypoport.local:/opt/instances/vgl_allgemein/webapps
# scp target/dateviewer.war vgl_bv@solv168.hypoport.local:/opt/instances/vgl_bv/webapps

FINDMOON=$(L findmoon)
WARFILE=$(L warfile)
USER=langha_l
PATHNAME=/var/lib/tomcat-7-testing/webapps
SSH_OPT=

DEBUGGING=

$DEBUGGING ssh $SSH_OPT $USER@$FINDMOON  -p 21007 /etc/init.d/tomcat-7-testing stop
$DEBUGGING scp ${WARFILE} $USER@${FINDMOON}:$PATHNAME
$DEBUGGING ssh $SSH_OPT $USER@$FINDMOON  -p 21007 /etc/init.d/tomcat-7-testing start
