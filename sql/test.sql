select
  id,
  access_token_validity_seconds,
  additional_information,
  authorities,
  authorized_grant_types,
  auto_approve_scope,
  client_id,
  client_secret,
  refresh_token_validity_seconds,
  registered_redirect_uri,
  resource_ids,
  scope
from test.t_oauth_client_details;

insert into t_oauth_client_details
(
  id,
  client_id,
  client_secret,
  access_token_validity_seconds,
  refresh_token_validity_seconds,
  authorized_grant_types

)
values
  (
    '1',
    'client',
    'secret',
    7200,
    604800,
    'authorization_code,refresh_token,password'

  )
