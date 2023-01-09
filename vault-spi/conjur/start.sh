#!/bin/bash

FILE=$(mktemp)
trap 'rm -rf -- "$FILE"' EXIT

make all

podman exec -ti conjur conjurctl account create demoAccount > $FILE

APIKEY=$(sed -n '/API/ s/.*: *//p' $FILE)

# TODO refactor
TOKEN=$(curl -k --header "Accept-Encoding: base64" -d $APIKEY https://localhost:18443/authn/demoAccount/admin/authenticate)
curl -k -H "Authorization: Token token=\"${TOKEN}\"" -d "$(< policy.yml)" https://localhost:18443/policies/demoAccount/policy/root
curl -k -H "Authorization: Token token=\"${TOKEN}\"" -d "password" https://localhost:18443/secrets/demoAccount/variable/ldap/password
#curl -k -H "Authorization: Token token=\"${TOKEN}\"" https://localhost:18443/secrets/demoAccount/variable/ldap/password
