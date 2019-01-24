import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Jerk from './jerk';
import JerkDetail from './jerk-detail';
import JerkUpdate from './jerk-update';
import JerkDeleteDialog from './jerk-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={JerkUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={JerkUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={JerkDetail} />
      <ErrorBoundaryRoute path={match.url} component={Jerk} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={JerkDeleteDialog} />
  </>
);

export default Routes;
