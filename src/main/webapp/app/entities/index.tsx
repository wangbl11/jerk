import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Jerk from './jerk';
import Preference from './preference';
import Registration from './registration';
import Tag from './tag';
import Setting from './setting';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/jerk`} component={Jerk} />
      <ErrorBoundaryRoute path={`${match.url}/preference`} component={Preference} />
      <ErrorBoundaryRoute path={`${match.url}/registration`} component={Registration} />
      <ErrorBoundaryRoute path={`${match.url}/tag`} component={Tag} />
      <ErrorBoundaryRoute path={`${match.url}/setting`} component={Setting} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
