import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Setting from './setting';
import SettingDetail from './setting-detail';
import SettingUpdate from './setting-update';
import SettingDeleteDialog from './setting-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SettingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SettingUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SettingDetail} />
      <ErrorBoundaryRoute path={match.url} component={Setting} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SettingDeleteDialog} />
  </>
);

export default Routes;
