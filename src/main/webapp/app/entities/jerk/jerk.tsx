import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import {
  Translate,
  translate,
  TextFormat,
  ICrudSearchAction,
  ICrudGetAllAction,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities, updateEntity } from './jerk.reducer';
import { IJerk } from 'app/shared/model/jerk.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IJerkProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IJerkState extends IPaginationBaseState {
  search: string;
}

export class Jerk extends React.Component<IJerkProps, IJerkState> {
  state: IJerkState = {
    search: '',
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  search = () => {
    if (this.state.search) {
      this.setState({ activePage: 1 }, () => {
        const { activePage, itemsPerPage, sort, order, search } = this.state;
        this.props.getSearchEntities(search, activePage - 1, itemsPerPage, `${sort},${order}`);
      });
    }
  };

  clear = () => {
    this.setState({ search: '', activePage: 1 }, () => {
      this.props.getEntities();
    });
  };

  handleSearch = event => this.setState({ search: event.target.value });

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };
  setActive(jerk, sts) {
    jerk.authStatus = sts ? 'A1' : 'A0';
    this.props.updateEntity(jerk);
  }
  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order, search } = this.state;
    if (search) {
      this.props.getSearchEntities(search, activePage - 1, itemsPerPage, `${sort},${order}`);
    } else {
      this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
    }
  };

  render() {
    const { jerkList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="jerk-heading">
          <Translate contentKey="jerkkApp.jerk.home.title">Jerks</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey={`jerkkApp.jerk.home.createLabel`}>Create new Jerk</Translate>
          </Link>
        </h2>
        <Row>
          <Col sm="12">
            <AvForm onSubmit={this.search}>
              <AvGroup>
                <InputGroup>
                  <AvInput
                    type="text"
                    name="search"
                    value={this.state.search}
                    onChange={this.handleSearch}
                    placeholder={translate('jerkkApp.jerk.home.search')}
                  />
                  <Button className="input-group-addon">
                    <FontAwesomeIcon icon="search" />
                  </Button>
                  <Button type="reset" className="input-group-addon" onClick={this.clear}>
                    <FontAwesomeIcon icon="trash" />
                  </Button>
                </InputGroup>
              </AvGroup>
            </AvForm>
          </Col>
        </Row>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('username')}>
                  <Translate contentKey="jerkkApp.jerk.username">Username</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('displayname')}>
                  <Translate contentKey="jerkkApp.jerk.displayname">Displayname</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('authStatus')}>
                  <Translate contentKey="jerkkApp.jerk.authStatus">Auth Status</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jerkkApp.jerk.jerkInfo">Registration Info</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="jerkkApp.jerk.jerkType">Registration Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('createdDate')}>
                  <Translate contentKey="jerkkApp.jerk.createdDate">Created Date</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('modifiedDate')}>
                  <Translate contentKey="jerkkApp.jerk.modifiedDate">Modified Date</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {jerkList.map((jerk, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${jerk.id}`} color="link" size="sm">
                      {jerk.id}
                    </Button>
                  </td>
                  <td>{jerk.username}</td>

                  <td dangerouslySetInnerHTML={{ __html: jerk.displayname }} />
                  <td>
                    {jerk.authStatus === 'A0' ? (
                      <button onClick={() => this.setActive(jerk, true)} className="btn btn-danger btn-sm">
                        <Translate contentKey={`jerkkApp.AuthStatusEnum.${jerk.authStatus}`} />
                      </button>
                    ) : (
                      <button onClick={() => this.setActive(jerk, false)} className="btn btn-danger btn-sm">
                        <Translate contentKey={`jerkkApp.AuthStatusEnum.${jerk.authStatus}`} />
                      </button>
                    )}
                  </td>
                  <td>
                    {jerk.jerkInfo ? (
                      jerk.jerkInfo.fbzt === 1 ? (
                        <Link to={`/entity/registration/${jerk.jerkInfo.id}`} className="btn btn-primary">
                          <Translate contentKey="jerkkApp.jerk.online">Published</Translate>
                        </Link>
                      ) : (
                        <Link to={`/entity/registration/${jerk.jerkInfo.id}`} className="btn btn-primary">
                          <Translate contentKey="jerkkApp.jerk.offline">Draft</Translate>
                        </Link>
                      )
                    ) : (
                      <Link to={`/entity/registration/${jerk.id}/new`} className="btn btn-primary">
                        <Translate contentKey="jerkkApp.jerk.nonregistration">No Registration</Translate>
                      </Link>
                    )}
                  </td>
                  <td>
                    <Translate contentKey={`jerkkApp.RegistrationTypeEnum.${jerk.jerkInfo ? jerk.jerkInfo.registType : null}`} />
                  </td>
                  <td>
                    <TextFormat value={jerk.createdDate} type="date" format={APP_DATE_FORMAT} blankOnInvalid />
                  </td>
                  <td>
                    <TextFormat value={jerk.modifiedDate} type="date" format={APP_DATE_FORMAT} blankOnInvalid />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${jerk.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${jerk.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${jerk.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ jerk }: IRootState) => ({
  jerkList: jerk.entities,
  totalItems: jerk.totalItems
});

const mapDispatchToProps = {
  getSearchEntities,
  getEntities,
  updateEntity
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Jerk);
