<template>
  <q-card class="font-5 no-shadow no-border">
    <div class="row q-pa-md">
      <div class="col">
        <q-table
          title="Combustible"
          :columns="columnas"
          rows-per-page-label="Registros por pagina"
          no-data-label="Sin datos para mostrar"
          :pagination="paginacion"
          hide-no-data
          :rows="combustible"
          row-key="id"
        >
          <template v-slot:top-left>
            <div class="column">
              <p class="text-h5">Combustible</p>
              <q-btn class="paleta2-fondo2 paleta1-color1 q-mb-lg" icon="add_circle" label="Nuevo combustible" @click="fMostrarNuevoCombustible" />
            </div>
          </template>
          <template v-slot:top-right>
            <div class="column items-end">
              <div class="q-my-md">
                <q-btn-dropdown class="paleta2-fondo2 paleta1-color1" label="Buscar combustible por" dropdown-icon="fa-solid fa-magnifying-glass">
                  <q-list>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarCaja">
                      <q-item-section avatar>
                        <q-icon name="paid" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Caja</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarCamion">
                      <q-item-section avatar>
                        <q-icon name="fa-solid fa-truck-fast" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Camion</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarConductor">
                      <q-item-section avatar>
                        <q-icon name="airline_seat_recline_extra" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Conductor</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarFecha">
                      <q-item-section avatar>
                        <q-icon name="fa-solid fa-calendar-days" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Fecha</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarKilometros">
                      <q-item-section avatar>
                        <q-icon name="pin" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Kilometros</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarLitros">
                      <q-item-section avatar>
                        <q-icon name="fa-solid fa-gas-pump" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Litros</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarNotas">
                      <q-item-section avatar>
                        <q-icon name="fa-solid fa-pen-to-square" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Notas</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarPrecio">
                      <q-item-section avatar>
                        <q-icon name="fa-solid fa-money-bill-1-wave" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Precio</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarProveedor">
                      <q-item-section avatar>
                        <q-icon name="storefront" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Proveedor</q-item-label>
                      </q-item-section>
                    </q-item>
                  </q-list>
                </q-btn-dropdown>
              </div>
              <div class="col">
                <q-select
                  v-if="editCaja"
                  outlined
                  dense
                  emit-value
                  map-options
                  clearable
                  v-model="caja"
                  option-value="id"
                  option-label="caja"
                  label="Buscar por caja"
                  use-input
                  hide-selected
                  fill-input
                  :options="cajas"
                  @filter="fFiltrarCajas"
                  @update:model-value="afBuscarPorCajaId()"
                  hint="Tenés que escribir al menos 3 caracteres para buscar."
                >
                  <template v-slot:no-option>
                    <q-item>
                      <q-item-section class="text-grey"> Sin resultados </q-item-section>
                    </q-item>
                  </template>
                </q-select>
                <q-select
                  v-if="editCamion"
                  outlined
                  dense
                  emit-value
                  map-options
                  clearable
                  v-model="camion"
                  option-value="id"
                  option-label="camion"
                  label="Buscar por camion"
                  use-input
                  hide-selected
                  fill-input
                  :options="camiones"
                  @filter="fFiltrarCamiones"
                  @update:model-value="afBuscarPorCamionId()"
                  hint="Tenés que escribir al menos 3 caracteres para buscar."
                >
                  <template v-slot:no-option>
                    <q-item>
                      <q-item-section class="text-grey"> Sin resultados </q-item-section>
                    </q-item>
                  </template>
                </q-select>
                <q-select
                  v-if="editConductor"
                  outlined
                  dense
                  emit-value
                  map-options
                  clearable
                  v-model="conductor"
                  :options="conductores"
                  option-value="id"
                  option-label="nombre"
                  label="Buscar por conductor"
                  use-input
                  input-debounce="0"
                  @filter="fFiltrarConductores"
                  @update:model-value="afBuscarPorConductorId()"
                  hint="Tenés que escribir al menos 3 caracteres para buscar."
                >
                  <template v-slot:no-option>
                    <q-item>
                      <q-item-section class="text-grey"> Sin resultados </q-item-section>
                    </q-item>
                  </template>
                </q-select>
                <div class="column" v-if="editFecha">
                  <div class="row justify-around">
                    <q-input
                      mask="##-##-####"
                      style="width: 180px"
                      v-model="fecha.from"
                      outlined
                      dense
                      clearable
                      label="Fecha fin"
                      hint="20-01-2020"
                    >
                      <template v-slot:before>
                        <q-icon name="event" class="cursor-pointer">
                          <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                            <q-date v-model="fecha.from" mask="DD-MM-YYYY" :locale="myLocale">
                              <div class="row items-center justify-end">
                                <q-btn v-close-popup label="OK" color="primary" flat />
                              </div>
                            </q-date>
                          </q-popup-proxy>
                        </q-icon>
                      </template>
                    </q-input>
                    <q-input
                      class="q-ml-md"
                      mask="##-##-####"
                      style="width: 180px"
                      v-model="fecha.to"
                      outlined
                      dense
                      clearable
                      label="Fecha inicio"
                      hint="30-01-2020"
                    >
                      <template v-slot:before>
                        <q-icon name="event" class="cursor-pointer">
                          <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                            <q-date v-model="fecha.to" mask="DD-MM-YYYY" :locale="myLocale">
                              <div class="row items-center justify-end">
                                <q-btn v-close-popup label="OK" color="primary" flat />
                              </div>
                            </q-date>
                          </q-popup-proxy>
                        </q-icon>
                      </template>
                    </q-input>
                    <div class="col">
                      <q-icon
                        name="fa-solid fa-magnifying-glass"
                        size="24px"
                        class="cursor-pointer q-pa-sm edits"
                        v-on:click="afBuscarPorFecha()"
                      />
                    </div>
                  </div>
                </div>
                <div class="column" v-if="editKilometros">
                  <div class="row justify-between">
                    <div class="col">
                      <q-chip
                        v-model:selected="kilometroChip.izq"
                        :class="{ 'paleta2-fondo2': kilometroChip.izq, 'edits-fondo': !kilometroChip.izq }"
                        text-color="white"
                        size="12px"
                        icon="fa-solid fa-minus"
                        @update:selected="afBuscarPorKilometro()"
                      >
                        Mínimo
                      </q-chip>
                    </div>
                    <div class="edits col text-center">
                      <span>Buscar por kilometro</span><br />
                      <q-icon name="fa-solid fa-plus-minus" />
                    </div>
                    <div class="col text-right">
                      <q-chip
                        v-model:selected="kilometroChip.der"
                        :class="{ 'paleta2-fondo2': kilometroChip.der, 'edits-fondo': !kilometroChip.der }"
                        text-color="white"
                        size="12px"
                        icon="fa-solid fa-plus"
                        @update:selected="afBuscarPorKilometro()"
                      >
                        Máximo
                      </q-chip>
                    </div>
                  </div>
                  <div class="row">
                    <q-range
                      label-always
                      switch-label-side
                      color="grey-6"
                      v-model="kilometro"
                      :min="1"
                      :max="1000"
                      label
                      @change="(kilometroChip.izq = false), (kilometroChip.der = false)"
                    />
                  </div>
                </div>
                <div class="column" v-if="editLitros">
                  <div class="row justify-between">
                    <div class="col">
                      <q-chip
                        v-model:selected="litrosChip.izq"
                        :class="{ 'paleta2-fondo2': litrosChip.izq, 'edits-fondo': !litrosChip.izq }"
                        text-color="white"
                        size="12px"
                        icon="fa-solid fa-minus"
                        @update:selected="afBuscarPorLitros()"
                      >
                        Mínimo
                      </q-chip>
                    </div>
                    <div class="edits col text-center">
                      <span>Buscar por litros</span><br />
                      <q-icon name="fa-solid fa-plus-minus" />
                    </div>
                    <div class="col text-right">
                      <q-chip
                        v-model:selected="litrosChip.der"
                        :class="{ 'paleta2-fondo2': litrosChip.der, 'edits-fondo': !litrosChip.der }"
                        text-color="white"
                        size="12px"
                        icon="fa-solid fa-plus"
                        @update:selected="afBuscarPorLitros()"
                      >
                        Máximo
                      </q-chip>
                    </div>
                  </div>
                  <div class="row">
                    <q-range
                      label-always
                      switch-label-side
                      color="grey-6"
                      v-model="litro"
                      :min="1"
                      :max="1000"
                      label
                      @change="(litrosChip.izq = false), (litrosChip.der = false)"
                    />
                  </div>
                </div>
                <q-input
                  v-if="editNotas"
                  outlined
                  dense
                  clearable
                  v-on:keyup.enter="afBuscarPorNotas()"
                  v-model="notas"
                  label="Buscar por notas"
                  hint="Tenés que escribir al menos 3 caracteres para buscar."
                >
                  <template v-slot:no-option>
                    <q-item>
                      <q-item-section class="text-grey"> Sin resultados </q-item-section>
                    </q-item>
                  </template>
                  <template v-slot:after>
                    <q-icon
                      name="fa-solid fa-magnifying-glass"
                      class="q-mx-xs"
                      v-on:click="afBuscarPorNotas()"
                      style="cursor: pointer"
                    />
                  </template>
                </q-input>
                <div class="column" v-if="editPrecio">
                  <div class="row justify-between">
                    <div class="col">
                      <q-chip
                        v-model:selected="precioChip.izq"
                        :class="{ 'paleta2-fondo2': precioChip.izq, 'edits-fondo': !precioChip.izq }"
                        text-color="white"
                        size="12px"
                        icon="fa-solid fa-minus"
                        @update:selected="afBuscarPorPrecio()"
                      >
                        Mínimo
                      </q-chip>
                    </div>
                    <div class="edits col text-center">
                      <span>Buscar por precio</span><br />
                      <q-icon name="fa-solid fa-plus-minus" />
                    </div>
                    <div class="col text-right">
                      <q-chip
                        v-model:selected="precioChip.der"
                        :class="{ 'paleta2-fondo2': precioChip.der, 'edits-fondo': !precioChip.der }"
                        text-color="white"
                        size="12px"
                        icon="fa-solid fa-plus"
                        @update:selected="afBuscarPorPrecio()"
                      >
                        Máximo
                      </q-chip>
                    </div>
                  </div>
                  <div class="row">
                    <q-range
                      label-always
                      switch-label-side
                      color="grey-6"
                      v-model="precio"
                      :min="1"
                      :max="1000"
                      label
                      @change="(precioChip.izq = false), (precioChip.der = false)"
                    />
                  </div>
                </div>
                <q-select
                  v-if="editProveedor"
                  outlined
                  dense
                  emit-value
                  map-options
                  clearable
                  v-model="proveedor"
                  :options="proveedores"
                  option-value="id"
                  option-label="nombre"
                  label="Buscar por proveedor"
                  use-input
                  input-debounce="0"
                  @filter="fFiltrarProveedores"
                  @update:model-value="afBuscarPorProveedorId()"
                  hint="Tenés que escribir al menos 3 caracteres para buscar."
                >
                  <template v-slot:no-option>
                    <q-item>
                      <q-item-section class="text-grey"> Sin resultados </q-item-section>
                    </q-item>
                  </template>
                </q-select>
              </div>
            </div>
          </template>
          <template v-slot:body="props">
            <q-tr :props="props" :class="(props.row.eliminada === null) ? '':'bg-red-2'">
              <q-td auto-width class="text-center">
                <q-btn
                  size="sm"
                  class="text-white q-mr-xs"
                  :class="props.expand ? 'paleta5-fondo3' : 'paleta5-fondo2'"
                  round
                  dense
                  @click="props.expand = !props.expand"
                >
                  <q-icon size="2em" class="q-pa-xs" :name="props.expand ? 'zoom_out' : 'zoom_in'" />
                  <q-tooltip>
                    Expandir
                  </q-tooltip>
                </q-btn>
                <q-btn
                  v-if="props.row.eliminada === null && (autoridad === 'admin' || autoridad === 'usuario')"
                  size="sm"
                  class="text-white paleta5-fondo2 q-mr-xs"
                  round
                  dense
                  @click="fMostrarEditarCombustible(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="edit" />
                  <q-tooltip>
                    Modificar
                  </q-tooltip>
                </q-btn>
                <q-btn
                  v-if="props.row.eliminada === null && autoridad === 'admin'"
                  size="sm"
                  class="text-white paleta5-fondo2 q-mr-xs"
                  round
                  dense
                  @click="fMostrarEliminarCombustible(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="delete" />
                  <q-tooltip>
                    Eliminar
                  </q-tooltip>
                </q-btn>
                <q-btn
                  v-if="props.row.eliminada !== null && autoridad === 'admin'"
                  size="sm"
                  class="text-white paleta5-fondo2 q-mr-xs"
                  round
                  dense
                  @click="fMostrarReciclarCombustible(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="recycling" />
                  <q-tooltip>
                    Reciclar
                  </q-tooltip>
                </q-btn>
              </q-td>
              <q-td class="text-center">
                {{ fFormatoFecha(props.row.fecha) }}
              </q-td>
              <q-td>
                {{ props.row.camion }}
              </q-td>
              <q-td>
                {{ props.row.conductor }}
              </q-td>
              <q-td class="text-center">
                {{ props.row.litros }}
              </q-td>
              <q-td class="text-center">
                {{ props.row.precio }}
              </q-td>
              <q-td class="text-center">
                {{ props.row.kilometros }}
              </q-td>
              <q-td class="text-center">
                {{ props.row.precio * props.row.kilometros }}
              </q-td>
            </q-tr>
            <q-tr v-show="props.expand" :props="props" class="paleta5-fondo2">
              <q-td colspan="100%">
                <div class="row">
                  <div v-if="props.row.id != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.id }}</div>
                    <div class="row paleta1-color2">Id</div>
                  </div>
                  <div v-if="props.row.caja != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.caja }}</div>
                    <div class="row paleta1-color2">Caja</div>
                  </div>
                  <div v-if="props.row.camion != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.camion }}</div>
                    <div class="row paleta1-color2">Camión</div>
                  </div>
                  <div v-if="props.row.conductor != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.conductor }}</div>
                    <div class="row paleta1-color2">Conductor</div>
                  </div>
                  <div v-if="props.row.fecha != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ fFormatoFecha(props.row.fecha) }}</div>
                    <div class="row paleta1-color2">Fecha</div>
                  </div>
                  <div v-if="props.row.kilometros != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ fFormatoFecha(props.row.kilometros) }}</div>
                    <div class="row paleta1-color2">Kilometros</div>
                  </div>
                  <div v-if="props.row.litros != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ fFormatoFecha(props.row.litros) }}</div>
                    <div class="row paleta1-color2">Litros</div>
                  </div>
                  <div v-if="props.row.precio != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ props.row.precio }}</div>
                    <div class="row paleta1-color2">Precio por litro</div>
                  </div>
                  <div v-if="props.row.proveedor != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ fFormatoFecha(props.row.proveedor) }}</div>
                    <div class="row paleta1-color2">Proveedor</div>
                  </div>
                  <div v-if="props.row.creador != null && autoridad === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.creador }}</div>
                    <div class="row paleta1-color2">Creador</div>
                  </div>
                  <div v-if="props.row.creada != null && autoridad === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ fFormatoFecha(props.row.creada) }}</div>
                    <div class="row paleta1-color2">Creado</div>
                  </div>
                  <div v-if="props.row.modificador != null && autoridad === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.modificador }}</div>
                    <div class="row paleta1-color2">Modificador</div>
                  </div>
                  <div v-if="props.row.modificada != null && autoridad === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ fFormatoFecha(props.row.modificada) }}</div>
                    <div class="row paleta1-color2">Modificado</div>
                  </div>
                  <div v-if="props.row.eliminador != null && autoridad === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.eliminador }}</div>
                    <div class="row paleta1-color2">Eliminador</div>
                  </div>
                  <div v-if="props.row.eliminada != null && autoridad === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ fFormatoFecha(props.row.eliminada) }}</div>
                    <div class="row paleta1-color2">Eliminada</div>
                  </div>
                  <div v-if="props.row.notas != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.notas }}</div>
                    <div class="row paleta1-color2">Notas</div>
                  </div>
                </div>
              </q-td>
            </q-tr>
          </template>
        </q-table>
      </div>
    </div>
  </q-card>

  <q-dialog v-model="nuevoCombustibleDialog" persistent transition-show="fade" transition-hide="fade">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">{{ titulo }}</div>
        <q-space />
        <q-btn class="text-grey-8" icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row justify-around">
        <q-icon name="img:/icons/numeros/number1.svg" size="3em" class="svg-primary" :class="{ 'svg-accent': paso1 }" />
      </div>
      <q-card-section v-if="paso1">
        <q-form v-on:submit.prevent="fGuardarCombustible">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                mask="##-##-####"
                v-model="combustibleCreation.fecha"
                :rules="[reglas.requerido]"
                outlined
                dense
                readonly
                clearable
                label="Fecha"
                hint="Formato dd-mm-yyyy."
              >
                <template v-slot:prepend>
                  <q-icon name="event" class="cursor-pointer">
                    <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                      <q-date v-model="combustibleCreation.fecha" mask="DD-MM-YYYY" :locale="myLocale">
                        <div class="row items-center justify-end">
                          <q-btn v-close-popup label="OK" color="primary" flat />
                        </div>
                      </q-date>
                    </q-popup-proxy>
                  </q-icon>
                </template>
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="combustibleCreation.cajaId"
                :rules="[reglas.requerido]"
                :options="cajas"
                option-value="id"
                option-label="caja"
                label="Caja"
                use-input
                input-debounce="0"
                @filter="fFiltrarCajas"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="combustibleCreation.camionId"
                :rules="[reglas.requerido]"
                :options="camiones"
                option-value="id"
                option-label="camion"
                label="Camion"
                use-input
                input-debounce="0"
                @filter="fFiltrarCamiones"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="combustibleCreation.conductorId"
                :rules="[reglas.requerido]"
                :options="conductores"
                option-value="id"
                option-label="conductor"
                label="Conductor"
                use-input
                input-debounce="0"
                @filter="fFiltrarConductores"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="combustibleCreation.proveedorId"
                :rules="[reglas.requerido]"
                :options="proveedores"
                option-value="id"
                option-label="proveedor"
                label="Proveedor"
                use-input
                input-debounce="0"
                @filter="fFiltrarProveedores"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="combustibleCreation.precio"
                :rules="[reglas.requerido]"
                mask="#.##"
                fill-mask="0"
                reverse-fill-mask
                outlined
                dense
                clearable
                label="Precio por litro"
                hint="Ingresá un número."
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="combustibleCreation.litros"
                :rules="[reglas.requerido]"
                mask="#.##"
                fill-mask="0"
                reverse-fill-mask
                outlined
                dense
                clearable
                label="Litros"
                hint="Ingresá un número."
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="combustibleCreation.kilometros"
                :rules="[reglas.requerido]"
                mask="#.##"
                fill-mask="0"
                reverse-fill-mask
                outlined
                dense
                clearable
                label="Kilometros al momento de carga"
                hint="Ingresá un número."
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                type="textarea"
                v-model="combustibleCreation.notas"
                autogrow
                outlined
                dense
                clearable
                label="Notas"
              />
            </div>
          </div>
          <div class="row justify-between q-pa-md">
            <q-btn class="paleta2-color2 bg-white" v-close-popup icon="close" ripple>
              Cerrar
            </q-btn>
            <q-btn class="paleta2-fondo2 text-white" type="submit" icon-right="save" ripple >
              Finalizar
            </q-btn>
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>

import { ayuda } from 'app/src/helpers/ayuda'
import { cajaService } from 'src/services/caja_service'
import { camionService } from 'src/services/camion_service'
import { CombustibleCreation } from 'src/models/creation/combustible_creation'
import { combustibleService } from 'src/services/combustible_service'
import { conductorService } from 'src/services/conductor_service'
import { llaveroService } from 'src/helpers/llavero_service'
import { notificarService } from 'src/helpers/notificar_service'
import { proveedorService } from 'src/services/proveedor_service'
import { reactive, ref } from 'vue'
import { reglasValidacion } from 'src/helpers/reglas_validacion'
import { useQuasar } from 'quasar'

const paginacion = {
  rowsPerPage: 50,
  sortBy: 'id',
  descending: true
}

const columnas = [
  {
    label: 'Acciones',
    align: 'center'
  },
  {
    name: 'fecha',
    label: 'Fecha',
    align: 'center',
    field: 'fecha',
    sortable: true
  },
  {
    name: 'camion',
    label: 'Camión',
    align: 'left',
    field: 'camion',
    sortable: true
  },
  {
    name: 'conductor',
    label: 'Conductor',
    align: 'left',
    field: 'conductor',
    sortable: true
  },
  {
    name: 'litros',
    label: 'Litros',
    align: 'center',
    field: 'litros',
    sortable: true
  },
  {
    name: 'precio',
    label: 'Precio',
    align: 'center',
    field: 'precio',
    sortable: true
  },
  {
    name: 'kilometros',
    label: 'Kilometros',
    align: 'center',
    field: 'kilometros',
    sortable: true
  },
  {
    name: 'total',
    label: 'Total',
    align: 'center',
    field: 'total',
    sortable: true
  }
]

export default {
  setup () {
    const $q = useQuasar()

    const autoridad = ref(ayuda.getAutoridad())
    const caja = ref(null)
    const cajas = ref([])
    const cajasList = ref([])
    const camion = ref(null)
    const camiones = ref([])
    const camionesList = ref([])
    const combustible = ref([])
    const combustibleCreation = reactive(new CombustibleCreation())
    const conductor = ref(null)
    const conductores = ref([])
    const conductoresList = ref([])
    const descripcion = ref(null)
    const editCaja = ref(false)
    const editCamion = ref(false)
    const editConductor = ref(false)
    const editFecha = ref(false)
    const editKilometros = ref(false)
    const editLitros = ref(false)
    const editNotas = ref(false)
    const editPrecio = ref(false)
    const editProveedor = ref(false)
    const fecha = ref({ from: null, to: null })
    const kilometros = ref({ min: 1, max: 500000 })
    const kilometrosChip = ref({ izq: false, der: false })
    const litros = ref({ min: 1, max: 1000 })
    const litrosChip = ref({ izq: false, der: false })
    const precio = ref({ min: 1, max: 1000 })
    const precioChip = ref({ izq: false, der: false })
    const notas = ref(null)
    const nuevoCombustibleDialog = ref(false)
    const paso1 = ref(true)
    const proveedor = ref(null)
    const proveedores = ref([])
    const proveedoresList = ref([])
    const reglas = reactive(reglasValidacion.reglas)
    const sesion = ref(ayuda.getUid())
    const titulo = ref(null)

    afBuscarPaginadas()

    async function afBuscarCajas () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value === 'admin') {
          if (llaveroService.obtenerDeLocalConSesion('hhCajaTodasConEliminadasConSesion', sesion.value) !== null) {
            cajasList.value = llaveroService.obtenerDeLocalConSesion('hhCajaTodasConEliminadasConSesion', sesion.value).value
            console.log('CajaService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await cajaService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              cajasList.value = resultado.data
              console.log('CajaService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhCajaTodasConSesion', sesion.value) !== null) {
            cajasList.value = llaveroService.obtenerDeLocalConSesion('hhCajaTodasConSesion', sesion.value).value
            console.log('CajaService: Sesion recargada.')
          } else {
            resultado = await cajaService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              cajasList.value = resultado.data
              console.log('CajaService: ' + resultado.headers.mensaje)
            }
          }
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarCamiones () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value === 'admin') {
          if (llaveroService.obtenerDeLocalConSesion('hhCamionTodasConEliminadasConSesion', sesion.value) !== null) {
            camionesList.value = llaveroService.obtenerDeLocalConSesion('hhCamionTodasConEliminadasConSesion', sesion.value).value
            console.log('CamionService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await camionService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              camionesList.value = resultado.data
              console.log('CamionService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhCamionTodasConSesion', sesion.value) !== null) {
            camionesList.value = llaveroService.obtenerDeLocalConSesion('hhCamionTodasConSesion', sesion.value).value
            console.log('CamionService: Sesion recargada.')
          } else {
            resultado = await camionService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              camionesList.value = resultado.data
              console.log('CamionService: ' + resultado.headers.mensaje)
            }
          }
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarConductores () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value === 'admin') {
          if (llaveroService.obtenerDeLocalConSesion('hhConductorTodasConEliminadasConSesion', sesion.value) !== null) {
            conductoresList.value = llaveroService.obtenerDeLocalConSesion('hhConductorTodasConEliminadasConSesion', sesion.value).value
            console.log('ConductorService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await conductorService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              conductoresList.value = resultado.data
              console.log('ConductorService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhConductorTodasConSesion', sesion.value) !== null) {
            conductoresList.value = llaveroService.obtenerDeLocalConSesion('hhConductorTodasConSesion', sesion.value).value
            console.log('ConductorService: Sesion recargada.')
          } else {
            resultado = await conductorService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              conductoresList.value = resultado.data
              console.log('ConductorService: ' + resultado.headers.mensaje)
            }
          }
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarProveedores () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value === 'admin') {
          if (llaveroService.obtenerDeLocalConSesion('hhProveedorTodasConEliminadasConSesion', sesion.value) !== null) {
            proveedoresList.value = llaveroService.obtenerDeLocalConSesion('hhProveedorTodasConEliminadasConSesion', sesion.value).value
            console.log('ProveedorService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await proveedorService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              proveedoresList.value = resultado.data
              console.log('ProveedorService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhProveedorTodasConSesion', sesion.value) !== null) {
            proveedoresList.value = llaveroService.obtenerDeLocalConSesion('hhProveedorTodasConSesion', sesion.value).value
            console.log('ProveedorService: Sesion recargada.')
          } else {
            resultado = await proveedorService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              proveedoresList.value = resultado.data
              console.log('ProveedorService: ' + resultado.headers.mensaje)
            }
          }
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarPaginadas () {
      $q.loading.show()
      try {
        const paginadoDTO = {
          direccion: 'DESC',
          campo: 'creada',
          pagina: '0',
          elementos: '50'
        }
        let resultado = null
        if (autoridad.value === 'admin') {
          resultado = await combustibleService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await combustibleService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          combustible.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          combustible.value = []
          console.info(err.response.headers.mensaje)
          notificarService.infoAlerta(err.response.headers.mensaje)
        } else if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarPorCajaId () {
      if (caja.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await combustibleService.spfBuscarTodasPorCajaIdConEliminadas(caja.value)
          } else {
            resultado = await combustibleService.spfBuscarTodasPorCajaId(caja.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            combustible.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            combustible.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorCamionId () {
      if (camion.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await camionService.spfBuscarTodasPorCamionIdConEliminadas(camion.value)
          } else {
            resultado = await camionService.spfBuscarTodasPorCamionId(camion.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            combustible.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            combustible.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorConductorId () {
      if (conductor.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await combustibleService.spfBuscarTodasPorConductorIdConEliminadas(conductor.value)
          } else {
            resultado = await combustibleService.spfBuscarTodasPorConductorId(conductor.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            combustible.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            combustible.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorFecha () {
      if (fecha.value.from == null) {
        notificarService.notificarAlerta('El campo fecha desde no puede estar vacio.')
      } else if (fecha.value.to == null) {
        notificarService.notificarAlerta('El campo fecha hasta no puede estar vacio.')
      } else if (fecha.value.from > fecha.value.to) {
        notificarService.notificarAlerta('El campo fecha desde no puede ser mayor que fecha hasta.')
      } else {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await combustibleService.spfBuscarTodasPorFechaEntreConEliminadas(ayuda.fFormatearDeDatePicker(fecha.value.from), ayuda.fFormatearDeDatePicker(fecha.value.to))
          } else {
            resultado = await combustibleService.spfBuscarTodasPorFechaEntre(ayuda.fFormatearDeDatePicker(fecha.value.from), ayuda.fFormatearDeDatePicker(fecha.value.to))
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            combustible.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            combustible.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorKilometros () {
      if (kilometrosChip.value.izq === true && kilometrosChip.value.der === true) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await combustibleService.spfBuscarTodasPorKilometrosEntreConEliminadas(kilometros.value.min, kilometros.value.max)
          } else {
            resultado = await combustibleService.spfBuscarTodasPorKilometrosEntre(kilometros.value.min, kilometros.value.max)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            combustible.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            combustible.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
        kilometrosChip.value.izq = false
        kilometrosChip.value.der = false
      }
    }

    async function afBuscarPorLitros () {
      if (litrosChip.value.izq === true && litrosChip.value.der === true) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await combustibleService.spfBuscarTodasPorLitrosEntreConEliminadas(litros.value.min, litros.value.max)
          } else {
            resultado = await combustibleService.spfBuscarTodasPorLitrosEntre(litros.value.min, litros.value.max)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            combustible.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            combustible.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
        litrosChip.value.izq = false
        litrosChip.value.der = false
      }
    }

    async function afBuscarPorNotas () {
      if (notas.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await combustibleService.spfBuscarTodasPorNotasConEliminadas(notas.value)
          } else {
            resultado = await combustibleService.spfBuscarTodasPorNotas(notas.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            combustible.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            combustible.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorPrecio () {
      if (precioChip.value.izq === true && precioChip.value.der === true) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await combustibleService.spfBuscarTodasPorPrecioEntreConEliminadas(precio.value.min, precio.value.max)
          } else {
            resultado = await combustibleService.spfBuscarTodasPorPrecioEntre(precio.value.min, precio.value.max)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            combustible.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            combustible.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
        precioChip.value.izq = false
        precioChip.value.der = false
      }
    }

    async function afBuscarPorProveedorId () {
      if (proveedor.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await combustibleService.spfBuscarTodasPorProveedorIdConEliminadas(proveedor.value)
          } else {
            resultado = await combustibleService.spfBuscarTodasPorProveedorId(conductor.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            combustible.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            combustible.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afEliminarCombustible (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await combustibleService.spfBorrar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se borró correctamente el combustible.')
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          console.info(err.response.headers.mensaje)
          notificarService.infoAlerta(err.response.headers.mensaje)
        } else if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afGuardarCombustible () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await combustibleService.spfGuardar(combustibleCreation)
        if (resultado.status === 201) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se creó correctamente el combustible.')
        }
      } catch (err) {
        if (err.response.status === 404) {
          console.info(err.response.headers.mensaje)
          notificarService.infoAlerta(err.response.headers.mensaje)
        } else if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    function fFormatoFecha (fecha) {
      return ayuda.getDateWithFormat(fecha)
    }

    function fGuardarCombustible () {
      afGuardarCombustible().then(() => {
        afBuscarPaginadas().then(() => {
          nuevoCombustibleDialog.value = false
          fIrPaso1()
        })
      })
    }

    async function afReciclarCombustible (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await combustibleService.spfReciclar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se recicló correctamente el combustible.')
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          console.info(err.response.headers.mensaje)
          notificarService.infoAlerta(err.response.headers.mensaje)
        } else if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    function fFiltrarCajas (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        cajas.value = cajasList.value.filter(
          (v) => v.caja.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fFiltrarCamiones (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        camiones.value = camionesList.value.filter(
          (v) => v.marcaModelo.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fFiltrarConductores (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        conductores.value = conductoresList.value.filter(
          (v) => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fFiltrarProveedores (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        proveedores.value = proveedoresList.value.filter(
          (v) => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fIrPaso1 () {
      paso1.value = true
      fLimpiarFormulario()
    }

    function fLimpiarFormulario () {
      combustibleCreation.cajaId = null
      combustibleCreation.camionId = null
      combustibleCreation.conductorId = null
      combustibleCreation.fecha = null
      combustibleCreation.kilometros = false
      combustibleCreation.litros = null
      combustibleCreation.notas = null
      combustibleCreation.precio = null
      combustibleCreation.proveedorId = null

      combustibleCreation.id = null
      combustibleCreation.creadorId = null
      combustibleCreation.creador = null
      combustibleCreation.creada = null
      combustibleCreation.modificadorId = null
      combustibleCreation.modificador = null
      combustibleCreation.modificada = null
      combustibleCreation.eliminadorId = null
      combustibleCreation.eliminador = null
      combustibleCreation.eliminada = null
    }

    function fLimpiarInputs () {
      editCaja.value = false
      editCamion.value = false
      editConductor.value = false
      editFecha.value = false
      editKilometros.value = false
      editLitros.value = false
      editNotas.value = false
      editPrecio.value = false
      editProveedor.value = false

      caja.value = null
      camion.value = null
      conductor.value = null
      fecha.value.from = null
      fecha.value.to = null
      kilometros.value.min = 1
      kilometros.value.max = 500000
      kilometrosChip.value.izq = false
      kilometrosChip.value.der = false
      litros.value.min = 1
      litros.value.max = 1000
      litrosChip.value.izq = false
      litrosChip.value.der = false
      notas.value = null
      precio.value.min = 0
      precio.value.max = 1000
      precioChip.value.izq = false
      precioChip.value.der = false
      proveedor.value = null
    }

    function fMostrarCaja () {
      afBuscarCajas().then(() => {
        fLimpiarInputs()
        editCaja.value = true
      })
    }

    function fMostrarCamion () {
      afBuscarCamiones().then(() => {
        fLimpiarInputs()
        editCamion.value = true
      })
    }

    function fMostrarConductor () {
      afBuscarConductores().then(() => {
        fLimpiarInputs()
        editConductor.value = true
      })
    }

    function fMostrarFecha () {
      fLimpiarInputs()
      editFecha.value = true
    }

    function fMostrarKilometros () {
      fLimpiarInputs()
      editKilometros.value = true
    }

    function fMostrarLitros () {
      fLimpiarInputs()
      editLitros.value = true
    }

    function fMostrarNotas () {
      fLimpiarInputs()
      editNotas.value = true
    }

    function fMostrarPrecio () {
      fLimpiarInputs()
      editPrecio.value = true
    }

    function fMostrarProveedor () {
      afBuscarProveedores().then(() => {
        fLimpiarInputs()
        editProveedor.value = true
      })
    }

    function fMostrarNuevoCombustible () {
      afBuscarCajas().then(() => {
        afBuscarCamiones().then(() => {
          afBuscarConductores().then(() => {
            afBuscarProveedores().then(() => {
              titulo.value = 'Nuevo combustible'
              fLimpiarFormulario()
              fIrPaso1()
              nuevoCombustibleDialog.value = true
            })
          })
        })
      })
    }

    function fMostrarEditarCombustible (props) {
      titulo.value = 'Modificar combustible'
      combustibleCreation.cajaId = props.row.cajaId
      combustibleCreation.camionId = props.row.camionId
      combustibleCreation.conductorId = props.row.conductorId
      combustibleCreation.fecha = ayuda.fFormatearADatePicker(props.row.fecha.slice(0, 10))
      combustibleCreation.kilometros = props.row.kilometros
      combustibleCreation.litros = props.row.litros
      combustibleCreation.notas = props.row.notas
      combustibleCreation.precio = props.row.precio
      combustibleCreation.proveedorId = props.row.proveedorId

      combustibleCreation.id = props.row.id
      combustibleCreation.creada = props.row.creada
      combustibleCreation.creadorId = props.row.creadorId
      combustibleCreation.eliminada = props.row.eliminada
      combustibleCreation.eliminadorId = props.row.eliminadorId
      combustibleCreation.modificada = props.row.modificada
      combustibleCreation.modificadorId = props.row.modificadorId

      nuevoCombustibleDialog.value = true
    }

    function fMostrarEliminarCombustible (props) {
      notificarService.infoAlerta('No se puede eliminar el recurso.')
      // afEliminarCombustible(props.row.id).then(() => {
      //   afBuscarPaginadas().then(() => {

      //   })
      // })
    }

    function fMostrarReciclarCombustible (props) {
      afReciclarCombustible(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    return {
      autoridad,
      caja,
      cajas,
      cajasList,
      columnas,
      combustible,
      combustibleCreation,
      conductor,
      conductores,
      conductoresList,
      editCaja,
      editCamion,
      editConductor,
      editFecha,
      editKilometros,
      editLitros,
      editNotas,
      editPrecio,
      editProveedor,
      fecha,
      kilometros,
      kilometrosChip,
      litros,
      litrosChip,
      precio,
      precioChip,
      notas,
      nuevoCombustibleDialog,
      paginacion,
      paso1,
      reglas,
      titulo,

      afBuscarPorCajaId,
      afBuscarPorCamionId,
      afBuscarPorConductorId,
      afBuscarPorFecha,
      afBuscarPorKilometros,
      afBuscarPorLitros,
      afBuscarPorNotas,
      afBuscarPorPrecio,
      afBuscarPorProveedorId,
      fGuardarCombustible,
      fFiltrarCajas,
      fFiltrarCamiones,
      fFiltrarConductores,
      fFiltrarProveedores,
      fFormatoFecha,
      fMostrarCaja,
      fMostrarCamion,
      fMostrarConductor,
      fMostrarEditarCombustible,
      fMostrarEliminarCombustible,
      fMostrarFecha,
      fMostrarKilometros,
      fMostrarLitros,
      fMostrarNotas,
      fMostrarNuevoCombustible,
      fMostrarPrecio,
      fMostrarProveedor,
      fMostrarReciclarCombustible,

      myLocale: {
        /* starting with Sunday */
        days: 'Domingo_Lunes_Martes_Miércoles_Jueves_Viernes_Sábado'.split('_'),
        daysShort: 'Dom_Lun_Mar_Mié_Jue_Vie_Sáb'.split('_'),
        months: 'Enero_Febrero_Marzo_Abril_Mayo_Junio_Julio_Agosto_Septiembre_Octubre_Noviembre_Diciembre'.split(
          '_'
        ),
        monthsShort: 'Ene_Feb_Mar_Abr_May_Jun_Jul_Ago_Sep_Oct_Nov_Dic'.split('_'),
        firstDayOfWeek: 1, // 0-6, 0 - Sunday, 1 Monday, ...
        format24h: true,
        pluralDay: 'dias'
      }
    }
  }
}

</script>
<style scoped>
.edits-fondo {
  background: #9e9e9e;
}
.edits {
  color: #9e9e9e;
}
.q-btn-dropdown {
  width: 270px;
}
.item-lista {
  border-bottom: 2px solid white;
  padding: 5px;
}
.nuevo-input {
  width: 240px;
}
</style>
